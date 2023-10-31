package com.webflux.study.chapter10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
*  Schedulers.immediate() 예제
* */
@Slf4j
public class Example10_9 {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .fromArray(new Integer[] {1, 3, 5, 7})
                .publishOn(Schedulers.parallel())
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("# doOnNext filter: {}", data))
                // Schedulers.immediate()은 추가 스레드를 생성하지 않고 현재 스레드를 그대로 사용하여 작업 처리
                .publishOn(Schedulers.immediate())
                .map(data -> data * 10)
                .doOnNext(data -> log.info("# doOnNext map : {}", data))
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(200L);
    }
}
