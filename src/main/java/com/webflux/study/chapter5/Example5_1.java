package com.webflux.study.chapter5;

import reactor.core.publisher.Flux;

/*
*  Hello, Reactor 코드로 보는 Reactor 구성요소
* */
public class Example5_1 {

    public static void main(String[] args) {
        Flux<String> sequence = Flux.just("Hello", "Reactor");
        sequence.map(String::toLowerCase)
                .subscribe(System.out::println);
    }
}
