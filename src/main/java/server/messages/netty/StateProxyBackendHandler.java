package server.messages.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;

public class StateProxyBackendHandler extends ChannelInboundHandlerAdapter {
    private final ChannelGroup channels;

    StateProxyBackendHandler (ChannelGroup channels) {
        this.channels = channels;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(msg.toString());
        channels.writeAndFlush(msg);
    }
}
