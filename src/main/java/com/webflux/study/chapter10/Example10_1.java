package com.webflux.study.chapter10;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
*  subscribeOn() 예제 코드
* */
@Slf4j
public class Example10_1 {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[] {1, 3, 5, 7})
                // 구독이 발생한 직후 실행될 스레드를 지정하는 Operator
                .subscribeOn(Schedulers.boundedElastic())
                // 원본 Flux에서 emit 되는 데이터를 로그 출력
                .doOnNext(data -> log.info("# doOnNext: {}", data))
                // 구독이 발생한 시점에 추가적인 처리를 해줄 수 있다.$
                .doOnSubscribe(subscription -> log.info("# doOnSubscribe"))
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(1000L);
    }
}
