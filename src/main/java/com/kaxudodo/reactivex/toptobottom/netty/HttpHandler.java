package com.kaxudodo.reactivex.toptobottom.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.util.CharsetUtil.UTF_8;

/**
 * Created by aaron on 2018/6/28.
 */
@ChannelHandler.Sharable
public class HttpHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(HttpHandler.class);

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {

        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof HttpRequest) {

            sendResponse(ctx);
        }
    }

    private void sendResponse(ChannelHandlerContext ctx) {
        final DefaultFullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer("OK".getBytes(UTF_8)));
        response.headers().add("Content-length", 2);
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("Error", cause);
        ctx.close();

    }
}


