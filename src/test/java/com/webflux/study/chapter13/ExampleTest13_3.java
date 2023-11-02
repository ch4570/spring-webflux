package com.webflux.study.chapter13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ExampleTest13_3 {

    @Test
    void divideByTwoTest() {
        Flux<Integer> source = Flux.just(2, 4, 6, 8, 10);
        StepVerifier
                .create(GeneralTestExample.divideByTwo(source))
                .expectSubscription()
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
//                .expectNext(1, 2, 3, 4) 5번째 데이터에서 오류 발생하기 때문에 expectError 체인 필요
                .expectError()
                .verify();
    }
}
