package com.webflux.study.chapter13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class ExampleTest13_6 {

    @Test
    void getCOVID19CountTest() {
        StepVerifier
                .create(TimedBasedTestExample.getCOVID19Count(
                        Flux.interval(Duration.ofMinutes(1)).take(1)
                ))
                .expectSubscription()
                .expectNextCount(11)
                .expectComplete()
                .verify(Duration.ofSeconds(3)); // 3초 이내에 기대값의 평가가 이루어지지 않으면 테스트 실패
    }
}
