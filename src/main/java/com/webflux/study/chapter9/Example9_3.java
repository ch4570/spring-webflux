package com.webflux.study.chapter9;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import static reactor.core.publisher.Sinks.EmitFailureHandler.*;


/*
*  Sinks.One 예제 코드 1
* */
@Slf4j
public class Example9_3 {

    public static void main(String[] args) {
        /*
        *  Sinks.One으로 아무리 많은 데이터를 emit 한다고 하더라도
        *  처음 Emit한 데이터는 정상적으로 emit 되지만 나머지 데이터는 Drop 된다.
        * */
        Sinks.One<String> sinkOne = Sinks.one();
        Mono<String> mono = sinkOne.asMono();

        // EmitFailureHandler 객체는 Emit 도중 발생한 에러에 대해 빠르게 실패처리한다.
        sinkOne.emitValue("Hello Reactor", FAIL_FAST);
        sinkOne.emitValue("Hi Reactor", FAIL_FAST);

        mono.subscribe(data -> log.info("# Subscriber1 : {}", data));
        mono.subscribe(data -> log.info("# Subscriber2 : {}", data));
    }
}
