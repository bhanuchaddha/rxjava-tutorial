package com.lct.reactive.operators.creation;


import io.reactivex.Observable;

public class Defer_Operator {

    public static void main(String[] args) {
        Observable.just(blockingHeavyOperation())
                .subscribe(next -> System.out.println("[just] Received "+next));


        Observable.defer(()-> Observable.just(blockingHeavyOperation()))
                .subscribe(next -> System.out.println("[defer] Received "+next));
    }

    static String blockingHeavyOperation(){
        System.out.println("blockingHeavyOperation called.");
        return "<<HeavyOperation>>";
    }
}
