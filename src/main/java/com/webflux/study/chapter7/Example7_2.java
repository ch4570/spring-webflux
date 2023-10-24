package com.webflux.study.chapter7;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
*  Hot Sequence 예제
* */
@Slf4j
public class Example7_2 {

    public static void main(String[] args) throws InterruptedException {
        String[] singers = {"Singer A", "Singer B", "Singer C", "Singer D", "Singer E"};

        log.info("# Begin concert");

        Flux<String> concertFlux =
                Flux.fromArray(singers)
                        // 데이터 소스로 입력된 각 데이터의 emit을 일정시간 동안 지연
                        .delayElements(Duration.ofSeconds(1))
                        // Cold Sequence를 Hot Sequence로 동작하게 해 주는 Operator
                        .share();

        concertFlux.subscribe(
          singer -> log.info("# Subscriber 1 is watching {}'s song", singer)
        );

        Thread.sleep(2500);

        concertFlux.subscribe(
                singer -> log.info("# Subscriber2 is watching {}'s song", singer)
        );

        Thread.sleep(3000);
    }
}
