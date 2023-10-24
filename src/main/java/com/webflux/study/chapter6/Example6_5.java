package com.webflux.study.chapter6;

import reactor.core.publisher.Flux;

/*
*  Flux 기본 개념 예제
* */
public class Example6_5 {

    public static void main(String[] args) {
        Flux.fromArray(new Integer[] {3, 6, 7, 9})
                .filter(num -> num > 6)
                .map(num -> num * 2)
                .subscribe(System.out::println);
    }
}
