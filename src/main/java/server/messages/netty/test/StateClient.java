package server.messages.netty.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import server.messages.PBCreatureOuterClass;
import java.util.Random;

import java.util.concurrent.TimeUnit;

public class StateClient {
    public static void main (String[] args) throws Exception {
        new StateClient("127.0.0.1", 8000).run();
}

    private final String host;
    private final int port;

    public StateClient (String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run () throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer< SocketChannel > () {
                @Override
                protected void initChannel (SocketChannel arg0) throws Exception {
                    ChannelPipeline pipeline = arg0.pipeline();

                    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                    pipeline.addLast(new ProtobufEncoder());
                }
            });

            Channel channel = bootstrap.connect(host, port).sync().channel();

            int id = 0;
            Random r = new Random();

            // Simulated game loop
            while (true) {
                PBCreatureOuterClass.PBCreature c = PBCreatureOuterClass.PBCreature.newBuilder()
                        .setId(id++)
                        .setR(r.nextFloat()) // 0-1
                        .setX(r.nextFloat() * 10).setY(r.nextFloat() * 10) // 0-10
                        .setS(r.nextFloat()) /// 0-1
                        .build();

                ChannelFuture f = channel.writeAndFlush(c);

                f.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (!channelFuture.isSuccess()) {
                            System.out.println("Send failed: " + f.cause() );
                        }
                        else
                            System.out.println("Successful send");
                    }
                });

                TimeUnit.SECONDS.sleep(2);
            }
        }
        finally {
            group.shutdownGracefully();
        }
    }
}
