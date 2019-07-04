package com.lct.reactive;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import static com.lct.reactive.HotStreamCreator.sleep;

public class ColdAndHotStream {


    public static void main(String[] args) {

        coldStreamRunner();
        hotStreamRunner();
    }

    static void coldStreamRunner(){

        // Cold Stream
        //A cold stream restarts from the beginning for each subscriber, and every subscriber gets the full set of items
        Observable<String> coldStream = Observable.just("Wolverine", "Charls", "Storm", "Jean");

        coldStream.subscribe(
                i ->System.out.println("[A] Received: "+i),
                err ->System.out.println("[A] BOOM"),
                ()->System.out.println("[A] Completion")
        );

        coldStream
                .subscribe(
                        i ->System.out.println("[B] Received: "+i),
                        err ->System.out.println("[B] BOOM"),
                        ()->System.out.println("[B] Completion")
                );
    }

    static  void hotStreamRunner(){
        // HotStreams
        // Unlike cold streams, hot streams broadcast the same items to all listening subscribers. However, if a subscriber arrives later, it won’t receive the previous items. Logically, hot streams represent events or facts rather than known finite data sets.
        Observable<Integer> hotStream = HotStreamCreator.create();

        // Subscriber A
        Disposable disposableA = hotStream.subscribe(
                i ->System.out.println("[A] Received: "+i),
                err ->System.out.println("[A] BOOM"),
                ()->System.out.println("[A] Completion")
        );

        sleep(3); // wait for 3 sec


        // B started subscribing
        Disposable disposableB = hotStream.subscribe(
                i ->System.out.println("[B] Received: "+i),
                err ->System.out.println("[B] BOOM"),
                ()->System.out.println("[B] Completion")
        );

        // wait for 5 sec. in this duaraiton both A and B recived the events
        sleep(5);

        // Stop a's subscription
        disposableA.dispose();

        sleep(2);

        // Stop B's subscription
        disposableB.dispose();

    }


}
