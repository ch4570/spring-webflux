package com.webflux.study.chapter6;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
*  Flux 활용 예제
* */
public class Example6_6 {

    public static void main(String[] args) {
       Flux<String> flux = Mono.justOrEmpty("Steve")
                .concatWith(Mono.justOrEmpty("Jobs"));

                flux.subscribe(System.out::println);
    }
}
