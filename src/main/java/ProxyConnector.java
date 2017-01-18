import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import server.messages.PBGameStateOuterClass;

public class ProxyConnector {
    private final String host;
    private final int port;
    private EventLoopGroup group;
    Channel channel; // Channel connection to proxy server

    public ProxyConnector (String host, int port) {
        this.host = host;
        this.port = port;

        group = new NioEventLoopGroup();
    }

    public void connect () throws Exception {
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel arg0) throws Exception {
                            ChannelPipeline pipeline = arg0.pipeline();

                            pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                            pipeline.addLast(new ProtobufEncoder());
                        }
                    });

            channel = bootstrap.connect(host, port).sync().channel();
        }
        catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    public void send (PBGameStateOuterClass.PBGameState state) {
        ChannelFuture f = channel.writeAndFlush(state);

        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (!channelFuture.isSuccess()) {
                    System.out.println("Send failed: " + f.cause());
                } else
                    System.out.println("Successful send");
            }
        });
    }

    public void disconnect () {
        group.shutdownGracefully();

    }
}
