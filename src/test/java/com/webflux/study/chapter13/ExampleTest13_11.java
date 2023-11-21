package com.webflux.study.chapter13;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class ExampleTest13_11 {

    @Test
    void generateNumberTest() {
        StepVerifier
                .create(BackPressureTestExample.generateNumber(), 1L)
                .thenConsumeWhile(num -> num >= 1)
                .verifyComplete();
    }
}
