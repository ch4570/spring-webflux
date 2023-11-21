package com.webflux.study.chapter13;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class ExampleTest13_18 {

    @Test
    void divideByTwoTest() {
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier
                .create(GeneralTestExample.divideByTwo(source.flux()))
                .expectSubscription()
                .then(() -> source.emit(2, 4, 6, 8, 10))
                .expectNext(1, 2, 3, 4)
                .expectError()
                .verify();

    }
}
