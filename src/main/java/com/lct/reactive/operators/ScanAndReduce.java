package com.lct.reactive.operators;

import com.github.javafaker.Faker;
import io.reactivex.Flowable;

import java.util.HashSet;

/*
 * Scan and reduce are two very close operators. They both take a seed (there are variants without) and an accumulator function.
 * This function is called with the previous result and the item coming from the observed stream.
 *
 *
 * Run this example and check the difference. reduce is emitting a single value when the observed streams are completed.
 * scan emits all computed values, including the seed (0 (seed), 0 + 0, 0 + 1, 1 + 2…​).
 * */
public class ScanAndReduce {
    public static void main(String[] args) {

        Flowable.range(1, 10)
                .scan(100, (result, item) -> result + item)
                //.scan((result, item) -> result + item) // take initial value of result to 0
                .subscribe(next -> System.out.println("[Scan] Got " + next));

        Flowable.range(1, 10)
                .reduce((result, item) -> result + item)
                .subscribe(next -> System.out.println("[Reduce] Got " + next));

        characters()
                .reduce(new HashSet<String>(),(set, character)-> {
                    set.add(character);
                    return set;
                })
                .map(set-> set.size())
                .subscribe(next -> System.out.println("There are "+next+" unique characters"));
    }

    static Flowable<String> characters(){
        return Flowable.range(1, 120)
                .map(n-> Faker.instance().dragonBall().character());
    }
}
