package com.webflux.study.chapter6;

import reactor.core.publisher.Mono;

/*
*  Mono 기본 개념 예제 2
* */
public class Example6_2 {

    public static void main(String[] args) {
        Mono
                .empty()
                .subscribe(
                        none -> System.out.println("# emitted onNext signal"),
                        error -> {},
                        () -> System.out.println("# emitted onComplete signal")
                );
    }
}
