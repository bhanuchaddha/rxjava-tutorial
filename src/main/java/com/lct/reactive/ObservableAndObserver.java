package com.lct.reactive;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ObservableAndObserver {
    public static void main(String[] args) {

        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Emit  " + 10);

                emitter.onNext("Emit " + 50);

                //throw new RuntimeException("Error occurred.");

                emitter.onNext("Emit " + 100);

                emitter.onComplete();

            }
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {}

            @Override
            public void onNext(String s) {
                System.out.println("Received event: " + s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Seems like error has occurred "+ e.getMessage()+" No further event or OnComplete would be received.");
            }

            @Override
            public void onComplete() {
                System.out.println("Received onComplete no further events would be received further ");
            }
        };

        observable.subscribe(observer);
    }
}
