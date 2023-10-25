package com.webflux.study.chapter8;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/*
*  데이터 요청 개수를 조절하는 BackPressure 예제
* */
@Slf4j
public class Example8_1 {

    public static void main(String[] args) {
        Flux.range(1, 5)
                .doOnRequest(data -> log.info("# doOnRequest: {}", data))
                // 람다 표현식 대신에 Subscriber 구현체 제공이 가능하다.
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        // 최초 데이터 요청 개수를 제어
                        request(1);
                    }

                    @SneakyThrows
                    @Override
                    protected void hookOnNext(Integer value) {
                        Thread.sleep(2000L);
                        log.info("# hookOnNext: {}", value);
                        /*
                        *  Publisher가 emit한 데이터를 전달받아 처리한 후, 데이터를 요청한다.
                        *  데이터 요청 개수는 request 메서드를 이용해 제어한다.
                        * */
                        request(1);
                    }
                });
    }
}
