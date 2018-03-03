package com.webflux.controller;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Created by 一伞烟雨 on 2018/3/3.
 */
@RestController
public class IndexControler {
    @GetMapping("/hello")
    public Mono<String> sayHelloWorld() {
        return Mono.just("Hello World");
    }
    @RequestMapping("/randomNumbers")
    public Flux<ServerSentEvent<Integer>> randomNumbers() {
            return Flux.interval(Duration.ofSeconds(1))
                    .map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
                    .map(data -> ServerSentEvent.<Integer>builder()
                            .event("random")
                            .id(Long.toString(data.getT1()))
                            .data(data.getT2())
                            .build());
    }
}
