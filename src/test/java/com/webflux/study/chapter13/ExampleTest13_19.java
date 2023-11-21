package com.webflux.study.chapter13;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.util.List;

public class ExampleTest13_19 {

    @Test
    void divideByTwoTest() {
        TestPublisher<Integer> source =
                TestPublisher.createNoncompliant(TestPublisher.Violation.ALLOW_NULL);

        StepVerifier
                .create(GeneralTestExample.divideByTwo(source.flux()))
                .expectSubscription()
                .then(() -> {
                    getDataSource().stream()
                            .forEach(data -> source.next(data));
                    source.complete();
                })
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();

    }


    private static List<Integer> getDataSource() {
        return List.of(2, 4, 6, 8, null);
    }
}
