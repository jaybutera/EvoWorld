package server.messages.netty.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import server.messages.PBCreatureOuterClass;
import server.messages.netty.StateClient;
import server.messages.netty.StateClientInitializer;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class PlayerClientSim {
    public static void main (String[] args) throws Exception {
        new StateClient("127.0.0.1", 8002).run();
    }

    private final String host;
    private final int port;

    public PlayerClientSim(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run () throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel (SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new ProtobufVarint32FrameDecoder())
                                    .addLast(new ProtobufDecoder(PBCreatureOuterClass.PBCreature.getDefaultInstance()))
                                    .addLast(new PlayerClientHandler());
                        }
                    });

            // Start the client.
            ChannelFuture f = bootstrap.connect(host, port).sync(); // (5)

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
            //Channel channel = bootstrap.connect(host, port).sync().channel();
        }
        finally {
            group.shutdownGracefully();
        }
}}
