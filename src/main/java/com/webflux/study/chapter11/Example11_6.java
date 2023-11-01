package com.webflux.study.chapter11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


/*
*  Context 특징 예제 3
* */
@Slf4j
public class Example11_6 {
    public static void main(String[] args) throws InterruptedException {
        String key1 = "company";

        Mono
                .just("Steve")
//                .transformDeferredContextual(((stringMono, ctx) ->
//                        ctx.get("role")))
                /*
                *  flatMap Operator 내부에 있는 Sequence를 InnerSequence라고 부른다.
                *  InnerSequence 외부에서는 InnerSequence 내부 Context에 저장된 데이터를 읽을 수 없다.
                * */
                .flatMap(name ->
                        Mono.deferContextual(ctx ->
                                Mono
                                        .just(ctx.get(key1) + ", " + name)
                                        .transformDeferredContextual((mono, innerCtx) ->
                                                mono.map(data -> data + ", " + innerCtx.get("role")))
                                        .contextWrite(context -> context.put("role", "CEO"))))
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key1, "Apple"))
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(100L);
    }
}
