package com.webflux.study.chapter12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.logging.Level;

/*
*  log() Operator 활용 예제
* */
@Slf4j
public class Example12_7 {

    private static Map<String, String> fruits = Map.of(
            "banana", "바나나",
            "apple", "사과",
            "pear", "배",
            "grape", "포도");
    public static void main(String[] args) {
        Flux.fromArray(new String[] {"BANANAS", "APPLES", "PEARS", "MELONS"})
                .map(String::toLowerCase)
                .map(fruit -> fruit.substring(0, fruit.length() - 1))
                .log("Fruit.SubString", Level.FINE)
                .map(fruits::get)
                .subscribe(
                        log::info,
                        error -> log.error("# onError: ", error));
    }
}
