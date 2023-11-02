package com.webflux.study.chapter13;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class ExampleTest13_2 {

    @Test
    void sayHelloTest() {
        StepVerifier
                .create(GeneralTestExample.sayHello())
                .expectSubscription()
                .as("# expect subscription")
                .expectNext("Hi")
                .as("# expect Hi")
                .expectNext("Reactor")
                .as("# expect Reactor")
                .verifyComplete();
    }
}
