package com.webflux.study.chapter12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/*
*  checkpoint로 Traceback을 출력하는 예제 1
* */
@Slf4j
public class Example12_2 {

    public static void main(String[] args) {
        Flux
                .just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x/y)
                .map(num -> num + 2)
                // 실제 에러가 발생한 assembly 지점 또는 에러가 전파된 assembly 지점의 traceback이 추가된다.
                .checkpoint()
                .subscribe(
                        data -> log.info("# onNext: {}", data),
                        error -> log.error("# onError: ", error));
    }
}
