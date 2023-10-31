package com.webflux.study.chapter11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/*
*  ContextView API 사용 예제
* */
@Slf4j
public class Example11_3 {
    public static void main(String[] args) throws InterruptedException {
        final String key1 = "company";
        final String key2 = "firstName";
        final String key3 = "lastName";

        Mono
                .deferContextual(ctx ->
                        Mono.just(ctx.get(key1) + ", " +
                                // Optional 객체를 리턴하므로, 디폴트 값 전달이 가능하다.
                                ctx.getOrEmpty(key2).orElse("no firstName") + " " +
                                // gerOrDefault API 또한 디폴트 값 전달이 가능하다.
                                ctx.getOrDefault(key3, "no lastName")))
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key1, "Apple"))
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(100L);
    }
}
