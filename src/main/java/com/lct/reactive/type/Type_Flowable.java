package com.lct.reactive.type;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/*
 * So far, we have seen examples of streams that were pushing items to subscribers.
 * However, there is an issue with this model. If your consumer cannot keep up with the pace, something bad is going to happen.
 * Putting a buffer in between will only handle small bumps. This is where back-pressure comes into the picture.
 * */
public class Type_Flowable {

    public static void main(String[] args) {

        // singleThreadExample();
        // multiThreadExampleUsingObservable();
        multiThreadExampleUsingFlowable();
    }


    //If you run this example, everything is fine.
    // Each emission is processed one by one, and one at a time from the source all the way down to the subscriber.
    // This is because a single thread is involved in the process, making everything synchronous.
    private static void singleThreadExample() {

        Observable.range(1, 10_000)
                .doOnNext(next -> System.out.println("Emiting event " + next))
                .subscribe(onNext -> {
                    System.out.println("Subscribed " + onNext);
                    wait(1);
                });
    }

    /*
     * If you run below you would see, subscriber would only be able to receive 1 event where as publisher have emitted all teh events.
     * The emissions of the numbers are too fast for the consumer, and because the emissions are being pushed into an unbounded buffer by observeOn,
     * this can be the source of many problems such as…​ running out of memory.
     *
     * To mitigate this issue, RX Java 2 provides a stream named Flowable. Flowable is like Observable (it may contain multiple items) but implements a back-pressure protocol. This protocol tells the source stream to emit items at a pace specified by the consumer. Flowable uses a protocol named Reactive Streams.
     * This specification has been introduced in Java 9 under the name java.util.concurrent.Flow and is becoming widely popular.
     * */
    private static void multiThreadExampleUsingObservable() {
        Observable.range(1, 10_000)
                .doOnNext(next -> System.out.println("Thread " + Thread.currentThread().getName() + ": Emitting event " + next))
                .observeOn(Schedulers.io()) // Observe on different thread
                .subscribe(onNext -> {
                    System.out.println("Thread " + Thread.currentThread().getName() + ": Revived " + onNext);
                    wait(1);
                });
    }

    // What we can see here is that the source emits a set of items (128) and then, 96 items have been processed by the rest of the flow.
    // During that time, no items have been emitted. So the consumer is telling to the source that it can’t handle more at that time, and the source stops emitting.
    // When the consumer can finally handle more items, it requests more to the source. No more risk of OOM!
    private static void multiThreadExampleUsingFlowable() {
        Flowable.range(1, 10_000)
                .doOnNext(next -> System.out.println("Thread " + Thread.currentThread().getName() + ": Emitting event " + next))
                .observeOn(Schedulers.io()) // Observe on different thread
                .subscribe(onNext -> {
                    System.out.println("Thread " + Thread.currentThread().getName() + ": Revived " + onNext);
                    wait(1);
                });

        // TODO: Why below
        // Wait for 20 seconds. Without this the process will terminate immediately.
        wait(20);
    }

    private static void wait(int sec) {
        System.out.println("Waiting for " + sec + " sec");
        try {
            Thread.sleep(sec * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
