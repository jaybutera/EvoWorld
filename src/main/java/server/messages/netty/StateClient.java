package server.messages.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import server.messages.PBCreatureOuterClass;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
                    .handler(new StateClientInitializer());

            Channel channel = bootstrap.connect(host, port).sync().channel();
            //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                PBCreatureOuterClass.PBCreature c = PBCreatureOuterClass.PBCreature.newBuilder()
                        .setId(1)
                        .setR(3.2f)
                        .setX(.2f).setY(.9f)
                        .setS(5.1f)
                        .build();

                //String s = in.readLine() + "\n";
                //System.out.println(s);
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
