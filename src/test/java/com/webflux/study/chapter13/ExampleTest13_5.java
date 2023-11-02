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
                .then(() -> VirtualTimeScheduler // 가상 스케줄러의 제어를 받게 해준다.
                        .get()
                        .advanceTimeBy(Duration.ofHours(1))) // 1시간을 당기는 작업을 수행한다.
                .expectNextCount(11)
                .expectComplete()
                .verify();
    }
}
