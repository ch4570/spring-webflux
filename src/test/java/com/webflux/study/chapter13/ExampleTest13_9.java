package com.webflux.study.chapter13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class ExampleTest13_9 {

    @Test
    void getVoteCountTest() {
        StepVerifier
                .withVirtualTime(() -> TimedBasedTestExample.getVoteCount(
                        Flux.interval(Duration.ofMinutes(1))
                ))
                .expectSubscription()
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNextCount(5)
                .expectComplete()
                .verify();

    }
}
