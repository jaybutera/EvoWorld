package server.messages.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;
import io.netty.util.concurrent.GlobalEventExecutor;
import server.messages.PBCreatureOuterClass;

public class StateHandler extends SimpleChannelInboundHandler<PBCreatureOuterClass.PBCreature> {
    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /*
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channels.add( ctx.channel() );
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channels.remove( ctx.channel() );
    }
    */

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //channels.add(ctx.channel());
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, PBCreatureOuterClass.PBCreature msg) {
        //ByteBuf in = (ByteBuf) msg;

        try {
            /*
            while (in.isReadable()) {
                System.out.print((char) in.readByte());
                System.out.flush();
            }
            */
            System.out.println(msg.toString());
        }
        finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    public ChannelGroup getChannels () {
        return channels;
    }
}
