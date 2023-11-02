package com.webflux.study.chapter13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;

public class ExampleTest13_5 {

    @Test
    void getCovid19CountTest() {
        StepVerifier
                .withVirtualTime(() -> TimedBasedTestExample.getCOVID19Count(
                        Flux.interval(Duration.ofHours(1)).take(1)
                ))
                .expectSubscription()
                .then(() -> VirtualTimeScheduler
                        .get()
                        .advanceTimeBy(Duration.ofHours(1)))
                .expectNextCount(11)
                .expectComplete()
                .verify();
    }
}
