package server.messages.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class StateClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive (ChannelHandlerContext ctx) throws Exception {
        System.out.println("Connected");
    }
}
