package com.kaxudodo.reactivex.toptobottom.clientside;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.client.HttpClient;
import io.reactivex.netty.protocol.http.client.HttpClientResponse;
import rx.Observable;

import static io.netty.util.CharsetUtil.UTF_8;

/**
 * Created by aaron on 2018/7/2.
 */
public class HttpClientRxNetty {

    public Observable<ByteBuf> test() {
        Observable<ByteBuf> response = HttpClient
                .newClient("localhost", 80)
                .createGet("/")
                .flatMap(HttpClientResponse::getContent);
        response.map(bb -> bb.toString(UTF_8)).subscribe(System.out::println);
        return response;
    }

    public static void main(String[] args) {
        HttpClientRxNetty httpClientRxNetty = new HttpClientRxNetty();
        httpClientRxNetty.test().subscribe();
    }
}
