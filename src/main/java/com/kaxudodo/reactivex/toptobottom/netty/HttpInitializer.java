package com.kaxudodo.reactivex.toptobottom.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Created by aaron on 2018/6/28.
 */
public class HttpInitializer extends ChannelInitializer<SocketChannel> {
    private final HttpHandler httpHandler = new HttpHandler();

    @Override public void initChannel(SocketChannel ch) {
        ch.pipeline() .addLast(new HttpServerCodec()) .addLast(httpHandler);

    }
}
