package com.webflux.study.chapter13;

import reactor.core.publisher.Mono;

import java.util.Base64;

public class ContextTestExample {

    public static Mono<String> getSecretMessage(Mono<String> keySource) {
        return keySource
                .zipWith(Mono.deferContextual(ctx ->
                        Mono.just((String)ctx.get("secretKey"))
                        ))
                .filter(tp ->
                        tp.getT1().equals(
                                new String(Base64.getDecoder().decode(tp.getT2()))))
                .transformDeferredContextual(
                        (mono, ctx) -> mono.map(notUse -> ctx.get("secretMessage"))
                );
    }
}
