package server.messages.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;

public class StateHandler extends SimpleChannelInboundHandler<String> {
    /*
    private static final ChannelGroup channels = new DefaultChannelGroup();

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
    public void channelRead0(ChannelHandlerContext ctx, String msg) {
        //ByteBuf in = (ByteBuf) msg;

        try {
            /*
            while (in.isReadable()) {
                System.out.print((char) in.readByte());
                System.out.flush();
            }
            */
            System.out.println(msg);
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
}
