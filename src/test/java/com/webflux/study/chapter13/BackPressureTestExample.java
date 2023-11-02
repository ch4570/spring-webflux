package com.webflux.study.chapter13;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class BackPressureTestExample {

    public static Flux<Integer> generateNumber() {
        return Flux
                .create(emitter -> {
                    for (int i=1; i<=100; i++) {
                        emitter.next(i);
                    }
                    emitter.complete();
                }, FluxSink.OverflowStrategy.ERROR);
    }
}
