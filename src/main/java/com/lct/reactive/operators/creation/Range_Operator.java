package com.lct.reactive.operators.creation;

import io.reactivex.Observable;

public class Range_Operator {
    public static void main(String[] args) {
        Observable.range(1,6)
                .subscribe(num-> System.out.println("Item "+num));
    }
}
