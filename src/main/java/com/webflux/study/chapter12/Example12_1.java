package com.webflux.study.chapter12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;

/*
*  Debug Mode 활성화를 이용한 디버깅 예제
* */
@Slf4j
public class Example12_1 {

    private static final Map<String, String> fruits = Map.of(
            "banana", "바나나",
            "apple", "사과",
            "pear", "배",
            "grape", "포도");

    public static void main(String[] args) throws InterruptedException {
        // 디버그 모드 활성화
        Hooks.onOperatorDebug();

        Flux
                .fromArray(new String[] {"BANANAS", "APPLES", "PEARS", "MELONS"})
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel())
                .map(String::toLowerCase)
                .map(fruit -> fruit.substring(0, fruit.length() - 1))
                .map(fruits::get)
                .map(translated -> "맛있는 " + translated)
                .subscribe(
                        log::info,
                        error -> log.error("# onError: ", error));

        Thread.sleep(100L);
    }
}
