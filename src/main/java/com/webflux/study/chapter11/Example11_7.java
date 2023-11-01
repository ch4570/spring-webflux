package com.webflux.study.chapter11;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/*
*  Context 활용 예제
* */
@Slf4j
public class Example11_7 {
    private static final String HEADER_AUTH_TOKEN = "authToken";
    public static void main(String[] args) {
        Mono<String> mono =
                postBook(Mono.just(
                        new Book("abcd-1111-3533-2809",
                                "Ractor's Bible",
                                "Kevin")))
                        .contextWrite(Context.of(HEADER_AUTH_TOKEN, "eyJhbGci0i"));

        mono.subscribe(data -> log.info("# onNext: {}", data));
    }

    private static Mono<String> postBook(Mono<Book> book) {
        return Mono
                // zip Operator를 활용해 Mono<Book> 객체와 인증 토큰 정보를 의미하는 Mono<String> 객체를 하나의 Mono로 합친다.
                .zip(book,
                        Mono
                                .deferContextual(ctx ->
                                        Mono.just(ctx.get(HEADER_AUTH_TOKEN))))
                .flatMap(tuple -> {
                    String response = "POST the book(" + tuple.getT1().getBookName() + ", " + tuple.getT1().getAuthor() + ") with token: " + tuple.getT2();
                    return Mono.just(response);
                });
    }
}

@Data
@AllArgsConstructor
class Book {
    private String isbn;
    private String bookName;
    private String author;
}
