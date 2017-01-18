package server.messages.netty.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import server.messages.PBCreatureOuterClass;
import server.messages.PBGameStateOuterClass;

public class PlayerClientHandler extends SimpleChannelInboundHandler<PBGameStateOuterClass.PBGameState> {
    @Override
    public void channelActive (ChannelHandlerContext ctx) throws Exception {
        System.out.println("Connected");
    }

    @Override
    public void channelRead0 (ChannelHandlerContext ctx, PBGameStateOuterClass.PBGameState msg) {
        System.out.println("Got message:");
        System.out.println(msg.toString());
    }
}
