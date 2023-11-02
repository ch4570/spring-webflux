package com.webflux.study.chapter13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class ExampleTest13_7 {

    @Test
    void getVoteCountTest() {
        StepVerifier
                .withVirtualTime(() -> TimedBasedTestExample.getVoteCount(
                        Flux.interval(Duration.ofMinutes(1))
                ))
                .expectSubscription()
                .expectNoEvent(Duration.ofMinutes(1)) // 지정한 시간동안 어떤 이벤트도 발생하지 않을 것이라고 기대하는 동시에 지정한 시간만큼 시간을 앞당긴다.
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNextCount(5)
                .expectComplete()
                .verify();
    }
}
