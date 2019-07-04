package com.lct.reactive;

import io.reactivex.Single;

/*
*
* Single - A stream emitting 1 item
* A Single is a specialized stream that only emits one item. It works like the Observable streams we have seen previously but is limited to operators that make sense for a single emission.
* Typically, doOnNext and doOnComplete are replaced by doOnSuccess that accept the produced item.
* */
public class Type_Single {

    public static void main(String[] args) {
        Single.just("Super Man")
                // For Single subscriber receive either success or error
                .subscribe(
                        successValue-> System.out.println("Today's superhero is "+successValue),
                        error-> System.out.println("BOOM: error"+error.getMessage())
                );
    }
}
