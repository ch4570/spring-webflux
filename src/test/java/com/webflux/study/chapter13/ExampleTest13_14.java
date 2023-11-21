package com.webflux.study.chapter13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ExampleTest13_14 {

    @Test
    public void getSecretMessageTest() {
        Mono<String> source = Mono.just("hello");

        StepVerifier
                .create(
                        ContextTestExample
                                .getSecretMessage(source)
                                .contextWrite(context ->
                                        context.put("secretMessage", "Hello Reactor"))
                                .contextWrite(context -> context.put("secretKey", "aGVsbG8=")))
                .expectSubscription()
                .expectAccessibleContext()
                .hasKey("secretKey")
                .hasKey("secretMessage")
                .then()
                .expectNext("Hello Reactor")
                .expectComplete()
                .verify();
    }
}
