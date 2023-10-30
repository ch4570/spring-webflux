package com.webflux.study.chapter9;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

/*
*  Sinks.Many 예제 코드 3
* */
@Slf4j
public class Example9_6 {

    public static void main(String[] args) {
        /*
        *   ReplaySpec은 emit된 데이터를 replay해서 이미 emit된 데이터라도 Subscriber가 전달받을 수 있게 한다.
        *   limit을 사용하면 특정 개수까지만 뒤로 돌려서 전달하지만, all 메서드를 사용하면 이미 emit된 데이터가 있어도
        *   처음 emit된 데이터부터 모든 데이터들이 구독자에게 전달된다.
        * */
        Sinks.Many<Integer> replaySink = Sinks.many().replay().limit(2);

        Flux<Integer> fluxView = replaySink.asFlux();

        replaySink.emitNext(1, FAIL_FAST);
        replaySink.emitNext(2, FAIL_FAST);
        replaySink.emitNext(3, FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscriber1: {}", data));

        replaySink.emitNext(4, FAIL_FAST);
        fluxView.subscribe(data -> log.info("# Subscriber2: {}", data));

    }
}
