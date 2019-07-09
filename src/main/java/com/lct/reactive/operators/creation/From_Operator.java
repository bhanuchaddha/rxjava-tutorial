package com.lct.reactive.operators.creation;

import io.reactivex.Observable;

import java.util.Arrays;

public class From_Operator {
    public static void main(String[] args) {
        Observable.fromArray( new String[]{"Superman", "Doctor Strange", "Iron Man"})
                .subscribe(event -> System.out.println(event));
    }
}
