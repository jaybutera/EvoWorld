package server.messages.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import server.messages.PBCreatureOuterClass;

public class StateServer {
    private static int FRONT_PORT = 8002;
    private static String BACK_HOST = "localhost";
    private static int BACK_PORT = 8000;

    public static void main(String[] args) throws Exception {
        new StateServer().run();
    }

    public void run() throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new SimpleChannelInboundHandler<PBCreatureOuterClass.PBCreature>() {
                                @Override
                                public void channelActive (ChannelHandlerContext ctx) {
                                    System.out.println("New player added to group");
                                    channels.add(ctx.channel());
                                }

                                @Override
                                public void channelRead0 (ChannelHandlerContext ctx, PBCreatureOuterClass.PBCreature msg) {
                                    /*
                                    try {
                                        System.out.println("Writing out...");
                                        channels.writeAndFlush(msg);
                                    }
                                    finally {
                                        ReferenceCountUtil.release(msg);
                                    }
                                    */
                                }

                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                                    cause.printStackTrace();
                                    ctx.close();
                                }
                            })
                            .addLast(new ProtobufVarint32LengthFieldPrepender())
                            .addLast(new ProtobufEncoder());
                        }
                    })
            .option(ChannelOption.SO_BACKLOG, 128)
            .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            Channel server_channel = bootstrap.bind(FRONT_PORT).sync()
                    .channel();//.closeFuture().sync();


            // Construct connection to backend (physics engine client)
            // -------------------------------------------------------
            final Channel back_channel;
            final EventLoop back_group = new DefaultEventLoop();

            ServerBootstrap back_connection = new ServerBootstrap()
                    .group(new NioEventLoopGroup())
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ProtobufVarint32FrameDecoder())
                                    .addLast(new ProtobufDecoder(PBCreatureOuterClass.PBCreature.getDefaultInstance()))
                                    .addLast(new SimpleChannelInboundHandler<PBCreatureOuterClass.PBCreature>() {
                                @Override
                                public void channelRead0(ChannelHandlerContext ctx, PBCreatureOuterClass.PBCreature msg) {
                                    System.out.println(msg.toString());
                                    channels.writeAndFlush(msg);
                                }
                            })
                            .addLast(new ProtobufVarint32LengthFieldPrepender())
                            .addLast(new ProtobufEncoder());
                        }
                    })
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.AUTO_READ, false);

            ChannelFuture f = back_connection.bind(BACK_HOST, BACK_PORT);
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

            // Close the frontend server
            server_channel.closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
