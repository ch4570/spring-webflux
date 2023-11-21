package com.webflux.study.chapter13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ExampleTest13_16 {

    @Test
    void getCountryTest() {
        StepVerifier
                .create(RecordTestExample.getCapitalizedCountry(
                        Flux.just("korea", "england", "canada", "india")))
                .expectSubscription()
                .recordWith(ArrayList::new)
                .thenConsumeWhile(country -> !country.isEmpty())
                .consumeRecordedWith(countries -> {
                    assertThat(
                            countries
                                    .stream()
                                    .allMatch(country ->
                                            Character.isUpperCase(country.charAt(0))),
                            is(true)
                    );
                })
                .expectComplete()
                .verify();
    }
}
