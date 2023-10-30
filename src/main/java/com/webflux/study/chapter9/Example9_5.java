package com.webflux.study.chapter9;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

/*
*  Sinks.Many 예제 코드 2
* */
@Slf4j
public class Example9_5 {

    public static void main(String[] args) {
        // MulticastSpec의 기능은 하나 이상의 Subscriber에게 데이터를 emit 한다.
        Sinks.Many<Integer> multicastSink = Sinks.many().multicast()
                .onBackpressureBuffer();

        Flux<Integer> fluxView = multicastSink.asFlux();

        multicastSink.emitNext(1, FAIL_FAST);
        multicastSink.emitNext(2, FAIL_FAST);

        /*
        *  Sinks가 Publisher의 역할을 할 경우 기본적으로 Hot Publisher로 동작
        *  onBackpressureBuffer() 메서드는 warm-up의 특징을 가지는 Hot Sequence로 동작한다.
        *  따라서 Subscriber2는 하나의 데이터(3)만 전달 받을 수 있다.
        * */
        fluxView.subscribe(data -> log.info("# Subscriber1: {}", data));
        fluxView.subscribe(data -> log.info("# Subscriber2: {}", data));

        multicastSink.emitNext(3, FAIL_FAST);

    }
}
