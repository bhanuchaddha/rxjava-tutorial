package com.lct.reactive;

import io.reactivex.Completable;

/*
*
* Completable represents a stream not emitting a value but simply concerned with an action being executed.
* As a consequence, it does not provide a doOnNext method as there is no next.
* It indicates the successful completion of a (potentially asynchronous) process or its failure
* */
public class Type_Completable {

    public static void main(String[] args) {
        Completable.fromAction(()-> System.out.println("I am action. I am getting completed"))
                .subscribe(
                        ()-> System.out.println("onCompleteCalled")
                        , onError-> System.out.println("OnError Called")
                );
    }
}
