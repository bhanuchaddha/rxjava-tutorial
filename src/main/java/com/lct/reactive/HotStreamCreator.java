package com.lct.reactive;

import io.reactivex.Observable;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

// Unlike cold streams, hot streams broadcast the same items to all listening subscribers.
// However, if a subscriber arrives later, it won’t receive the previous items.
// Logically, hot streams represent events or facts rather than known finite data sets.

public class HotStreamCreator {

    public static Observable<Integer>  create(){
        AtomicInteger counter = new AtomicInteger();
        AtomicInteger subscribers = new AtomicInteger();

        return Observable.<Integer>create(
                subscriber -> new Thread(()->{
                    while(subscribers.get()>0){
                        subscriber.onNext(counter.incrementAndGet());
                        sleep(1);
                    } }).start())
                .publish()
                .autoConnect()// Start publishing
                .doOnSubscribe(s -> subscribers.incrementAndGet())
                .doOnDispose(()-> System.out.println(" Subscriber is leaving. Subscriber count is now "+ subscribers.decrementAndGet()));

    }

    public static void sleep(Integer sec){
        try {
            Thread.sleep(sec *1000);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
