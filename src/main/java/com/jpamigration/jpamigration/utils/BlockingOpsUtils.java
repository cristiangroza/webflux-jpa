package com.jpamigration.jpamigration.utils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Callable;
import java.util.stream.Stream;

public final class BlockingOpsUtils {

    public static Mono<Void> execute(Runnable runnable) {
        return Mono.fromRunnable(runnable)
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }

    public static <T> Mono<T> executeToMono(Callable<T> callable) {
        return Mono.fromCallable(callable)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public static <T> Flux<T> executeToFlux(Callable<? extends Iterable<T>> callable) {
        return Mono.fromCallable(callable)
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(Schedulers.boundedElastic());
    }
    public static <T> Flux<T> fromStream(Callable<? extends Stream<T>> callable) {
        return Mono.fromCallable(callable)
                .flatMapMany(Flux::fromStream)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
