package com.kaxudodo.reactivex;

import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.reactivex.netty.protocol.tcp.server.TcpServer;
import rx.Observable;

import static io.netty.util.CharsetUtil.UTF_8;

/**
 * Created by aaron on 2018/6/8.
 */
public class ReactiveUtil {

    public static void log(Object msg) {
        System.out.println(System.currentTimeMillis()+ "   "+Thread.currentThread().getName() + ": " + msg );
    }

    public static void log(Object label,long start) {
        System.out.println( System.currentTimeMillis() - start +
                    "\t| " + Thread.currentThread().getName()
                    + "\t| " + label);
    }

    public static Observable<String> simple() {
        return Observable.create(subscriber -> {
            log("Subscribed");
            subscriber.onNext("A");
            subscriber.onNext("B");
//            subscriber.onCompleted();
        });
    }

    public static Observable<String> simpleAsync() {
        return Observable.create(subscriber -> {
            log("Subscribed");
            Runnable task = () -> {
                TcpServer
                        .newServer(8080)
                        .<String, String>pipelineConfigurator(pipeline -> {
                            pipeline.addLast(new LineBasedFrameDecoder(128));
                            pipeline.addLast(new StringDecoder(UTF_8));
                        })
                        .start(connection -> {
                            Observable<String> output = connection
                                    .getInput()
                                    .flatMap(line -> {
                                        if (line.isEmpty()) {
                                            subscriber.onNext("OKOK");
                                            return Observable.just("ok");
                                        } else {
                                            return Observable.empty();
                                        }
                                    });
                            return connection.writeAndFlushOnEach(output);
                        })
                        .awaitShutdown();
            };
            Thread thread = new Thread(task);
            thread.start();

//            subscriber.onCompleted();
        });
    }
}
