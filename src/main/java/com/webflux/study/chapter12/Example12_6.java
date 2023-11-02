package com.webflux.study.chapter12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/*
*  서로 다른 Operator 체인에서의 checkpoint 활용 예제
* */
@Slf4j
public class Example12_6 {
    public static void main(String[] args) {
        Flux<Integer> source = Flux.just(2, 4, 6, 8);
        Flux<Integer> other = Flux.just(1, 2, 3, 0);

        Flux<Integer> multiplySource = multiply(source, other).checkpoint();
        Flux<Integer> plusSource = plus(multiplySource).checkpoint();

        plusSource
                .subscribe(
                        data -> log.info("# onNext: {}", data),
                        error -> log.error("# onError: ", error));
    }

    private static Flux<Integer> multiply(Flux<Integer> source, Flux<Integer> other) {
        return source.zipWith(other, (x, y) -> x/y);
    }

    private static Flux<Integer> plus(Flux<Integer> source) {
        return source.map(num -> num + 2);
    }
}
