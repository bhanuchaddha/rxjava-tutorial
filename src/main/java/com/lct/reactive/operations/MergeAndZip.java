package com.lct.reactive.operations;

import com.github.javafaker.Faker;
import io.reactivex.Flowable;

/**
 * The mergeWith operator merges the current stream with other ones. The produced stream contains all the items from the merged streams in the order of their emissions.
 * If you prefer not mixing the items use concatWith but be aware it does not work for unbounded streams.
 *
 * The zipWith operator associates items from different streams. It takes the next items emitted by each stream and calls a function with all of them.
 */
public class MergeAndZip {

    public static void main(String[] args) {
        Flowable<String> beers = Flowable.range(1, 100)
                .map(n-> "Beer: "+Faker.instance().beer().name());

        Flowable<String> books = Flowable.range(1, 100)
                .map(n-> "Book: "+Faker.instance().book().title());

        beers.mergeWith(books)
                .filter(s -> s.contains("si")) // random filtering
                .subscribe(next -> System.out.println(next));

        beers.concatWith(books)
                .filter(s -> s.contains("si")) // random filtering
                .count()
                .subscribe(next -> System.out.println(next));

        //Zipping
        System.out.println("-----------------Zipping-------");
        Flowable<String> gotCharacters = Flowable.range(1, 10)
                .map(n-> Faker.instance().gameOfThrones().character());

        Flowable<String> gotDragons = Flowable.range(1, 10)
                .map(n-> Faker.instance().gameOfThrones().dragon());


        // we can use zip to combinedly iterate over two streams

        gotCharacters.zipWith(gotDragons, (c, d)-> c+" likes dragon "+d)
                .subscribe(next -> System.out.println(next));

    }
}
