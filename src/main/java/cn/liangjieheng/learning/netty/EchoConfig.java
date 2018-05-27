package cn.liangjieheng.learning.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

public class EchoConfig {

    private ChannelHandlerContext ctx;

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public void send(String msg) {
        ctx.writeAndFlush(Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8)));
    }
}
