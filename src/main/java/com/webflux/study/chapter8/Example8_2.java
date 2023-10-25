package com.webflux.study.chapter8;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/*
*  Backpressure ERROR 전략 예제
* */
@Slf4j
public class Example8_2 {

    public static void main(String[] args) throws InterruptedException {
        Flux
                // 0부터 1씩 증가한 숫자를 0.001초에 한 번씩 아주 빠른 속도로 emit
                .interval(Duration.ofMillis(1L))
                // ERROR 전략을 적용하기 위해서 onBackpressureError Operator를 사용
                .onBackpressureError()
                .doOnNext(data -> log.info("# doOnNext: {}", data))
                // Reactor Sequence 중 일부를 별도의 스레드에서 실행할 수 있도록 하는 Operator
                .publishOn(Schedulers.parallel())
                .subscribe(
                        data -> {
                            try {
                                // Subscriber가 전달받은 데이터를 처리하는데 0.005초가 걸리도록 시뮬레이션
                                Thread.sleep(5L);
                            } catch (InterruptedException e) {}
                            log.info("# onNext: {}", data);
                        },
                        error -> log.error("# onError", error));

        Thread.sleep(2000L);
    }
}
