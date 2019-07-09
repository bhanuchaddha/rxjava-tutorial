package com.lct.reactive.operators.creation;


import io.reactivex.Observable;

import java.util.Arrays;

public class Just_Operator {
    public static void main(String[] args) {
        Observable.just( "Superman", "Doctor Strange", "Iron Man")
                .subscribe(event -> System.out.println(event));

        Observable.just( new String[]{"Superman", "Doctor Strange", "Iron Man"})
                .subscribe(event -> System.out.println(Arrays.toString(event)));
    }
}
