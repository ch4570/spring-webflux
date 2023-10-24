package com.webflux.study.chapter6;


import reactor.core.publisher.Flux;

/*
*  Flux 활용 예제
* */
public class Example6_7 {

    public static void main(String[] args) {
        Flux.concat(
                Flux.just("Mercury", "Venus", "Earth"),
                Flux.just("Mars", "Jupiter", "Saturn")
                        .concatWith(Flux.just("Uranus", "Neptune", "Pluto")))
                .collectList()
                .subscribe(System.out::println);
    }
}
