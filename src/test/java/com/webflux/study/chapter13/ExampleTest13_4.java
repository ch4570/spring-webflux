package com.webflux.study.chapter13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class ExampleTest13_4 {

    @Test
    void tekeNumberTest() {
        Flux<Integer> source= Flux.range(0, 1000);

        StepVerifier
                .create(GeneralTestExample.takeNumber(source, 500), // 500개의 숫자 데이터를 emit
                        StepVerifierOptions.create().scenarioName("Verify from 0 to 499")
                        )
                .expectSubscription()
                .expectNext(0) // 첫 번째 emit된 숫자 0을 평가
                .expectNextCount(498) // 그 다음부터 emit된 데이터가 498개라고 기대 -> 내부적으로 498개의 숫자가 emit 된다
                .expectNext(500) // 499개의 데이터가 emit 되었는데 마지막 값이 499지만 500으로 기대해서 테스트 실패
                .expectComplete()
                .verify();

    }
}
