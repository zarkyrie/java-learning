package cn.liangjieheng.learning.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;

public class HeartBeatClientHandler extends ChannelInboundHandlerAdapter {


    private EchoConfig echoConfig;

    public HeartBeatClientHandler() {
    }

    public HeartBeatClientHandler(EchoConfig echoConfig) {
        this.echoConfig = echoConfig;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("激活时间是：" + new Date());
        System.out.println("HeartBeatClientHandler channelActive");
        echoConfig.setCtx(ctx);
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("停止时间是：" + new Date());
        System.out.println("HeartBeatClientHandler channelInactive");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf message = (ByteBuf) msg;
        System.out.println(message.toString());
//        ReferenceCountUtil.release(msg);
    }
}
