package com.webflux.study.chapter9;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import static reactor.core.publisher.Sinks.EmitFailureHandler.*;

/*
*  Sinks.Many 예제 코드 1
* */
@Slf4j
public class Example9_4 {

    public static void main(String[] args) {
        // Sinks.many()로 데이터를 emit할 경우, 세 가지 기능을 사용할 수 있다(unicast, multicast, reply)
        Sinks.Many<Integer> unicastSink = Sinks.many().unicast()
                .onBackpressureBuffer();

        Flux<Integer> fluxView = unicastSink.asFlux();

        // unicast는 one-to-one 방식이기 때문에 하나의 Subscriber만 허용한다.
        // 에러 -> Caused by: java.lang.IllegalStateException: Sinks.many().unicast() sinks only allow a single Subscriber
        unicastSink.emitNext(1, FAIL_FAST);
        unicastSink.emitNext(2, FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscriber1: {}", data));

        unicastSink.emitNext(3, FAIL_FAST);

//        fluxView.subscribe(data -> log.info("# Subscriber2: {}", data));

    }
}
