package com.webflux.study.chapter12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/*
*  checkpoint(description)로 description을 출력하는 예제
* */
@Slf4j
public class Example12_4 {
    public static void main(String[] args) {
        Flux
                .just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x/y)
                .checkpoint("Example12_4.zipWith.checkpoint")
                .map(num -> num + 2)
                .checkpoint("Example12_4.map.checkpoint")
                .subscribe(
                        data -> log.info("# onNext: {}", data),
                        error -> log.error("# onError: ", error));
    }
}
