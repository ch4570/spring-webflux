package com.webflux.study.chapter9;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

/*
*  create() Operator를 사용하는 예제
* */
@Slf4j
public class Example9_1 {

    public static void main(String[] args) throws InterruptedException {
      int tasks = 6;

        Flux
                // 처리해야 할 작업의 개수만큼 doTask 메서드를 호출
                .create((FluxSink<String> sink) -> {
                    IntStream
                            .range(1, tasks)
                            .forEach(n -> sink.next(doTasks(n)));
                })
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(n -> log.info("# created(): {}", n))
                .publishOn(Schedulers.parallel())
                // 처리 결과를 map Operator를 사용해 가공 처리
                .map(result -> result + " success!")
                .doOnNext(n -> log.info("# map(): {}", n))
                .publishOn(Schedulers.parallel())
                // 가공된 결과를 Subscriber 에서 소비
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(500L);
    }


    private static String doTasks(int taskNumber) {
        return "task " + taskNumber + " result";
    }
}
