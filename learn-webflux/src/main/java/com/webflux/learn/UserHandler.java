package com.webflux.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
/**
 * @author Created by 一伞烟雨 on 2018/3/3.
 */

@Service
public class UserHandler {
    @Autowired
    private UserRepository userRepository;

    public Mono<ServerResponse> handleGetUsers(ServerRequest request) {
        return ServerResponse.ok().body(userRepository.getUsers(), User.class);
    }

    public Mono<ServerResponse> handleGetUserById(ServerRequest request) {
        return userRepository.getUserById(request.pathVariable("id"))
                .flatMap(user -> ServerResponse.ok().body(Mono.just(user), User.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
