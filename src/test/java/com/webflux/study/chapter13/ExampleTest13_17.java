package com.webflux.study.chapter13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

public class ExampleTest13_17 {

    @Test
    void getCountryTest() {
        StepVerifier
                .create(RecordTestExample.getCapitalizedCountry(
                        Flux.just("korea", "england", "canada", "india")
                ))
                .expectSubscription()
                .recordWith(ArrayList::new)
                .thenConsumeWhile(country -> !country.isEmpty())
                .expectRecordedMatches(countries ->
                        countries
                                .stream()
                                .allMatch(country ->
                                        Character.isUpperCase(country.charAt(0))))
                .expectComplete()
                .verify();
    }
}
