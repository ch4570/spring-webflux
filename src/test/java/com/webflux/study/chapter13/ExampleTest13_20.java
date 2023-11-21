package com.webflux.study.chapter13;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

public class ExampleTest13_20 {

    @Test
    void publisherProbeTest() {
        PublisherProbe<String> probe =
                PublisherProbe.of(PublisherProbeTestExample.supplyStandByPower());

        StepVerifier
                .create(PublisherProbeTestExample
                        .processTask(
                                PublisherProbeTestExample.supplyMainPower(),
                                probe.mono()
                        ))
                .expectNextCount(1L)
                .verifyComplete();

        probe.assertWasSubscribed();
        probe.assertWasRequested();
        probe.assertWasNotCancelled();
    }
}
