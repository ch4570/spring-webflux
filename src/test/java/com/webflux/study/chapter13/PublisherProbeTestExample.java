package com.webflux.study.chapter13;

import reactor.core.publisher.Mono;

public class PublisherProbeTestExample {

    public static Mono<String> processTask(Mono<String> main, Mono<String> standBy) {
        return main
                .flatMap(Mono::just)
                .switchIfEmpty(standBy);
    }

    public static Mono<String> supplyMainPower() {
        return Mono.empty();
    }

    public static Mono<String> supplyStandByPower() {
        return Mono.just("# supply Stand Power");
    }
}
