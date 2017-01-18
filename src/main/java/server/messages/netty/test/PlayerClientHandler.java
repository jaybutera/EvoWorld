package server.messages.netty.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import server.messages.PBCreatureOuterClass;

public class PlayerClientHandler extends SimpleChannelInboundHandler<PBCreatureOuterClass.PBCreature> {
    @Override
    public void channelActive (ChannelHandlerContext ctx) throws Exception {
        System.out.println("Connected");
    }

    @Override
    public void channelRead0 (ChannelHandlerContext ctx, PBCreatureOuterClass.PBCreature msg) {
        System.out.println(msg.toString());
    }
}
