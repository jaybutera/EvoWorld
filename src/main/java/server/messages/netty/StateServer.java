package server.messages.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import server.messages.PBCreatureOuterClass;

import java.io.IOException;

public class StateServer {
    private static int FRONT_PORT = 8000;
    private static String BACK_HOST = "localhost";
    private static int BACK_PORT = 8002;

    public static void main(String[] args) throws Exception {
        new StateServer().run();
    }

    public void run() throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class);


            // ===========================================================
            // 1. define a separate thread pool to execute handlers with
            //    slow business logic. e.g database operation
            // ===========================================================
            //final EventExecutorGroup group = new DefaultEventExecutorGroup(1500); //thread pool of 1500

            StateHandler stateHandler = new StateHandler();

            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();

                    //pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                    pipeline.addLast(new serverDebugHandler());
                    //pipeline.addLast(new LineBasedFrameDecoder(80));
                    //pipeline.addLast(new StringDecoder());
                    pipeline.addLast(new ProtobufVarint32FrameDecoder());
                    pipeline.addLast(new ProtobufDecoder(PBCreatureOuterClass.PBCreature.getDefaultInstance()));
                    pipeline.addLast(stateHandler);

                    //===========================================================
                    // 2. run handler with slow business logic
                    //    in separate thread from I/O thread
                    //===========================================================
                    //pipeline.addLast(group,"serverHandler",new ServerHandler());
                }
            })
            .option(ChannelOption.SO_BACKLOG, 128)
            .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            bootstrap.bind(FRONT_PORT).sync()
                    .channel().closeFuture().sync();



            // Construct connection to backend (physics engine client)
            // -------------------------------------------------------
            final Channel back_channel;
            final EventLoop back_group = new DefaultEventLoop();

            Bootstrap back_connection = new Bootstrap()
                    .group(back_group)
                    .handler(new StateProxyBackendHandler(stateHandler.getChannels()))
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.AUTO_READ, false);

            ChannelFuture f = back_connection.connect(BACK_HOST, BACK_PORT);
            back_channel = f.channel();

            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        back_channel.read();
                    }
                    else {
                        back_channel.close();
                    }
                }
            });
            // -------------------------------------------------------
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
