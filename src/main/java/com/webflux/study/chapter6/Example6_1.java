package com.webflux.study.chapter6;

import reactor.core.publisher.Mono;

/*
*  Mno 기본 개념 예제 1
* */
public class Example6_1 {

    public static void main(String[] args) {
        Mono.just("Hello Reactor!")
                .subscribe(System.out::println);
    }
}
