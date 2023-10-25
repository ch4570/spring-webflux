package com.webflux.study.chapter8;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/*
*  Backpressure BUFFER_DROP_LATEST 전략 예제
* */
@Slf4j
public class Example8_5 {

    public static void main(String[] args) throws InterruptedException {
        Flux
                .interval(Duration.ofMillis(300L))
                .doOnNext(data -> log.info("# emitted by original Flux: {}", data))
                // onBackPressureBuffer Operator를 사용하여 BUFFER 전략 적용 -> 최대 용량 2
                .onBackpressureBuffer(2,
                        // 두 번째 파라미터를 통해 오버플로 발생시 Drop 되는 데이터를 전달받아 후처리가 가능하다.
                        dropped -> log.info("** Overflow & Dropped: {} **", dropped),
                        // Backpressure 전략을 지정할 수 있다.
                        BufferOverflowStrategy.DROP_LATEST)
                .doOnNext(data -> log.info("[ # emitted by Buffer: {} ]", data))
                .publishOn(Schedulers.parallel(), false, 1)
                .subscribe(
                        data -> {
                            try {
                                Thread.sleep(1000L);
                            } catch (InterruptedException e) {}
                            log.info("# onNext: {}", data);
                        },
                        error -> log.error("# onError", error));

        Thread.sleep(3000L);
    }
}
