package com.kaxudodo.threads.books;

import java.util.concurrent.*;

/**
 * Created by aaron on 2018/8/22.
 */
public class Memoizer<A,V>  implements Computable<A,V>{

    private final ConcurrentMap<A,Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A,V> c;
    public Memoizer(Computable<A,V> c){
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        while (true){
            Future<V> f = cache.get(arg);
            if(f == null){
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws InterruptedException {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<V>(eval);
                f = cache.putIfAbsent(arg,ft);
                if(f == null){
                    f = ft;ft.run();
                }
            }
            try{
                return f.get();
            }catch (CancellationException e){
                cache.remove(arg,f);
            }catch (ExecutionException e){
                throw launderThrowable(e.getCause());
            }
        }

    }

    public static RuntimeException launderThrowable(Throwable t){
        if(t instanceof RuntimeException)
            return (RuntimeException) t;
        else if ( t instanceof  Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Not unchecked",t);
    }
}
