package com.webflux.study.chapter10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
*  parallel() 예제 코드2
* */
@Slf4j
public class Example10_4 {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[] {1, 3, 5, 7, 9, 11, 13, 15, 17, 19})
                // 물리적인 스레드 전부를 사용할 필요가 없는 경우에는 스레드 개수를 지정할 수 있다.
                .parallel(4)
                .runOn(Schedulers.parallel())
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(100L);
    }
}
