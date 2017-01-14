package server.messages.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class serverDebugHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive (final ChannelHandlerContext ctx) {
        System.out.println("Client connected");
    }
}
