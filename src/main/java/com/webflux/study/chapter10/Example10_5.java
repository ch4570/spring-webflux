package com.webflux.study.chapter10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/*
*  publishOn()과 subscribeOn()을 사용하지 않을 경우
* */
@Slf4j
public class Example10_5 {
    public static void main(String[] args) {
        Flux
                .fromArray(new Integer[] {1, 3, 5, 7})
                .doOnNext(data -> log.info("# doOnNext fromArray: {}", data))
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("# doOnNext filter : {}", data))
                .map(data -> data * 10)
                .doOnNext(data -> log.info("# doOnNext map : {}", data))
                .subscribe(data -> log.info("# onNext: {}", data));
    }
}
