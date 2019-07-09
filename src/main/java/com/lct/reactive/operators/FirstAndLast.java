package com.lct.reactive.operators;

import io.reactivex.Observable;

/*
* On Observable and Flowable, there are a set of first operators that select the first items from a stream:
first(T def) takes the first item, or emits the given default if the source stream is empty. This method returns a Single.
firstElement() forwards the first item from the observed stream. This method returns a Maybe as the observed stream can be empty.
firstOrError() forwards the first item from the observed stream. If the observed stream is empty, it emits an error
The last / lastElement / lastOrError operators are the mirror operators forwarding only the last items of the stream.
*
* */
public class FirstAndLast {

    public static void main(String[] args) {

        // expected First
        Observable.just("First", "Second", "Third")
                .first("Default") // return single
                .subscribe(
                        single-> System.out.println("[first(default)] Single received with value "+ single)
                        , error-> System.out.println("[first(default)] Error received "+error)
                );

        // expected Default
        Observable.empty()
                .first("Default") // return single
                .subscribe(
                        single-> System.out.println("[first(default)] Single received with value "+ single)
                        , error-> System.out.println("[first(default)] Error received "+error)
                );

        // expected First
        Observable.just("First", "Second", "Third")
                .firstElement() // return MayBe
                .subscribe(
                        onSuccessValue-> System.out.println("[firstElement()] Single received with value "+ onSuccessValue)
                        , error-> System.out.println("[firstElement()] Error received "+error.getMessage())
                        , ()-> System.out.println("[firstElement()] No values received")
                );

        // expected onComplete
        Observable.empty()
                .firstElement() // return MayBe
                .subscribe(
                        onSuccessValue-> System.out.println("[firstElement()] Single received with value "+ onSuccessValue)
                        , error-> System.out.println("[firstElement()] Error received "+error.getMessage())
                        , ()-> System.out.println("[firstElement()] No values received")
                );




        // expected Third
        Observable.just("First", "Second", "Third")
                .last("Default") // return single
                .subscribe(
                        single-> System.out.println("[last(default)] Single received with value "+ single)
                        , error-> System.out.println("[last(default)] Error received "+error)
                );

        // expected Default
        Observable.empty()
                .last("Default") // return single
                .subscribe(
                        single-> System.out.println("[last(default)] Single received with value "+ single)
                        , error-> System.out.println("[last(default)] Error received "+error)
                );

        // expected Third
        Observable.just("First", "Second", "Third")
                .lastElement() // return MayBe
                .subscribe(
                        onSuccessValue-> System.out.println("[lastElement()] Maybe received with value "+ onSuccessValue)
                        , error-> System.out.println("[lastElement()] Error received "+error.getMessage())
                        , ()-> System.out.println("[lastElement()] No values received")
                );

        // expected onComplete
        Observable.empty()
                .firstElement() // return MayBe
                .subscribe(
                        onSuccessValue-> System.out.println("[lastElement()] Maybe received with value "+ onSuccessValue)
                        , error-> System.out.println("[lastElement()] Error received "+error.getMessage())
                        , ()-> System.out.println("[lastElement()] No values received")
                );

    }
}
