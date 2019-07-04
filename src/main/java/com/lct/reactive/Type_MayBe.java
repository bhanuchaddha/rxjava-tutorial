package com.lct.reactive;

import io.reactivex.Maybe;

import javax.sound.midi.Soundbank;

/*
* Maybe is a stream that can emit 0 or 1 item. It is useful because Single can’t emit null (null is an illegal value). Maybe observers are notified:

when a value is emitted using the onSuccess method,
when the stream complete, without a value using the onComplete method,
when an error is thrown using the onError method

Notice the subtlety about onSuccess and onComplete. The first one is called when there is a value. The second one is called when there is not

Maybe is often used for methods that may return null. For example, an asynchronous version of a findById method would return a Maybe.
Let’s use Maybe to check if there is a superhero named "Yoda" and another one named "Clement".
* */
public class Type_MayBe {

    public static void main(String[] args) {
        Maybe.just("SuperMan")
                .subscribe(
                        successValue-> System.out.println("[A] When value is there. onSeuccess id called "+ successValue)
                        , onError-> System.out.println("[A] When the id error, onError is called "+ onError.getMessage())
                        , () -> System.out.println("[A] When there is no value on complete is called")
                );

        Maybe.empty()
                .subscribe(
                        successValue-> System.out.println("[B] When value is there. onSeuccess id called "+ successValue)
                        , onError-> System.out.println("[B] When the id error, onError is called "+ onError.getMessage())
                        , () -> System.out.println("[B] When there is no value on complete is called")
                );
    }
}
