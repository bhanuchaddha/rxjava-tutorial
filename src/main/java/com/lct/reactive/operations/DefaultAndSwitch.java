package com.lct.reactive.operations;

/*
* Sometimes, when you realize that your stream is empty, you want to inject some sensible defaults.
* That’s what the defaultIfEmpty and switchIfEmpty operators are doing
* */

import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.AllArgsConstructor;

import javax.jws.soap.SOAPBinding;
import java.util.Arrays;
import java.util.List;

public class DefaultAndSwitch {

    public static void main(String[] args) {

        // Default
        getUser()
                .flatMap(user -> getAgreements(user))
                .flatMapObservable(list -> Observable.fromIterable(list))
                .filter(agreement -> agreement == 1000)
                .defaultIfEmpty(9999)
                .subscribe(onNext -> System.out.println("Default agreement emitted "+ onNext));

        // Switch
        getUser()
                .flatMap(user -> getAgreements(user))
                .flatMapObservable(list -> Observable.fromIterable(list))
                .filter(agreement -> agreement == 1000)
                .switchIfEmpty(getDefaultAgreement().toObservable())
                .subscribe(onNext -> System.out.println("Switched agreement emitted "+ onNext));

    }



    static Single<List<Integer>> getAgreements(User user) {
        return Single.just(Arrays.asList(111,222,333,444,555));
    }

    static Single<Integer> getDefaultAgreement(){
        return Single.just(9999);
    }

    static Single<User> getUser(){
        return Single.just(new User(1234));
    }

    @AllArgsConstructor
    static class User {
        int userId;
    }
}
