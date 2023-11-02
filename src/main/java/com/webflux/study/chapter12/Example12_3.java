package com.webflux.study.chapter12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/*
*  checkpoint()로 Traceback을 출력하는 예제 2
* */
@Slf4j
public class Example12_3 {
    public static void main(String[] args) {
        Flux
                .just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x/y)
                .checkpoint()
                .map(num -> num + 2)
                .checkpoint()
                .subscribe(
                        data -> log.info("# onNext: {}", data),
                        error -> log.error("# onError: ", error));
    }
}
