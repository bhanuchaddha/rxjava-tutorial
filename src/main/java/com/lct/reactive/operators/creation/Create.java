package com.lct.reactive.operators.creation;


import com.github.javafaker.Faker;
import io.reactivex.Observable;

public class Create {
    public static void main(String[] args) {

        // From Array
/*        Observable.fromArray(1, 2, 3)
                .map(val-> val*2)
                .subscribe(System.out::println);*/

        // each observable emit 3 types of events
        //onNext
        //onComplete
        //onError

        Faker faker = new Faker();
        Observable.range(10,10)
                .map(n-> {
                    if(n==15){
                        throw new IllegalStateException("number cannot be 15");
                    }
                    return n;
                })
                .map(n-> faker.dragonBall().character())

                // emmiter stop emmitting events if error occur
                .doOnNext(s-> System.out.println("Next event has been called   "+s))

                //if error occure onComplete is not called
                .doOnComplete(() -> System.out.println("complete event has been called"))

                // doMethods or any operator need to be configured after complete chain or after required operation has been done

                .doOnError(throwable -> System.out.println("doOnError::  "+throwable.getMessage()))
                .onErrorResumeNext(Observable.empty())

                .subscribe(System.out::println);

    }

    // we don't actually need doOnX methods to be configured in Observable. We can get those events in Subscriber as well
}
