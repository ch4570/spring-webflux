package com.webflux.study.chapter13;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class ExampleTest13_8 {

    @Test
    void generateNumberTest() {
        StepVerifier
                .create(BackPressureTestExample.generateNumber(), 1L) // 데이터 요청 개수를 1로 지정해서 테스트는 실패한다.
                .thenConsumeWhile(num -> num >= 1)
                .verifyComplete();
    }
}
