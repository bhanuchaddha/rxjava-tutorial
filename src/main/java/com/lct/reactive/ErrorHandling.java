package com.lct.reactive;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

import javax.naming.ServiceUnavailableException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
*onErrorReturnItem operator that let you recover from an error by injecting a default result.
* There is also a variant named onErrorReturn that let you decide of the value to return based on the error (Throwable).
*
* The onErrorResumeNext operator is similar to onErrorReturnItem but returns a stream.
* Basically, if an error happens, it replaces the stream with the given one.
*
* The last error recovery mechanism we are going to see is very powerful but can be dangerous too.
* The retry operator re-subscribes to a stream that emitted an error. Its behavior depends on the cold/hot nature of the observed stream.
* On a cold stream, the resubscription restarts the stream from the beginning. It’s very useful to retry a failed operation such as an HTTP call.
 * The retry operator has several variants letting you decide whether or not you want to retry based on the error and the number of attempts.
*
* */
public class ErrorHandling {

    public static void main(String[] args) {

        // Without error handling
        getAccounts()
                .subscribe(
                        onSuccess -> System.out.println("Accounts received "+ onSuccess)
                        , onError -> System.out.println("Error received "+ onError)
                );


        // With onErrorReturnItem
        getAccounts()
                .onErrorReturnItem(Arrays.asList("[With onErrorReturnItem]: Default","Accounts"))
                .subscribe(
                        onSuccess -> System.out.println("[With onErrorReturnItem]: Accounts received "+ onSuccess)
                        , onError -> System.out.println("[With onErrorReturnItem]: Error received "+ onError)
                );

        // With onErrorReturn you get the chance to evaluate the throwable. For example you can use it for logging
        getAccounts()
                .onErrorReturn(throwable -> {
                    System.out.println("[With onErrorReturn]: Error occurred");
                    return Collections.emptyList();
                })
                .subscribe(
                        onSuccess -> System.out.println("[With onErrorReturn]: Accounts received "+ onSuccess)
                        , onError -> System.out.println("[With onErrorReturn]: Error received "+ onError)
                );

        // With onErrorReturnNext you get call another stream
        getAccounts()
                .onErrorResumeNext(throwable -> {
                    System.out.println("[With onErrorReturnNext]: Logging error");
                    return Single.just(Arrays.asList("[With onErrorReturnNext]: Another"," Stream"));
                })
                .subscribe(
                        onSuccess -> System.out.println("[With onErrorReturnNext]: Accounts received "+ onSuccess)
                        , onError -> System.out.println("[With onErrorReturnNext]: Error received "+ onError)
                );

        // Retry
        //TODO:  Better understanding required
        getAccounts()
                .retry()
                //.observeOn(Schedulers.io())
                .subscribe(
                        onSuccess -> System.out.println("[With Retry]: Accounts received "+ onSuccess)
                        , onError -> System.out.println("[With Retry]: Error received "+ onError)
                );

    }

    static Single<List<String>> getAccounts(){
        return Single.error(ServiceUnavailableException::new);
    }
}
