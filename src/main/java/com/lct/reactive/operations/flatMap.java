package com.lct.reactive.operations;

/**
 * FlatMap takes each item emitted by the observable stream and maps it to another stream (this is the map part).
 * Then, it merges the emissions from the returned streams into a single stream
 *
 * by default flatMap returns a stream of the same type. So Single.flatMap returns a Single.
 * Fortunately, the method flatMapCompletable, flatMapMaybe and flatMapPublisher (for Flowable) let us transform the type of streams.
 *
 * FlatMap is also used as a composing operator to express a sequential composition. For example, chaining two HTTP requests
 */
public class flatMap {
}
