package com.webflux.study.chapter9;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

/*
*  Sinks를 사용하는 예제
* */
@Slf4j
public class Example9_2 {

    public static void main(String[] args) throws InterruptedException {
        int tasks = 6;

        Sinks.Many<String> unicastSink = Sinks.many().unicast()
                .onBackpressureBuffer();


        Flux<String> fluxView = unicastSink.asFlux();

        IntStream
                .range(1, tasks)
                .forEach(n -> {
                    // 루프를 돌 때마다 새로운 스레드에서 실행 -> (Thread0 ~ Thread4)
                    // Sinks는 프로그래밍 방식으로 Signal을 전송할 수 있으며, 스레드 안정성을 보장받을 수 있다.
                    try {
                        new Thread(() -> {
                            unicastSink.emitNext(doTasks(n), Sinks.EmitFailureHandler.FAIL_FAST);
                            log.info("# emitted: {}", n);
                        }).start();
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                });

        fluxView
                .publishOn(Schedulers.parallel())
                .map(result -> result + " success!")
                .doOnNext(n -> log.info("# map(): {}", n))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(200L);
    }

    private static String doTasks(int taskNumber) {
        return "task " + taskNumber + " result";
    }
}
