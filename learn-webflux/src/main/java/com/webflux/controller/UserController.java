package com.webflux.controller;

import com.webflux.exception.ResourceNotFoundException;
import com.webflux.learn.User;
import com.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author Created by 一伞烟雨 on 2018/3/3.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private  UserService userService;

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
    @ExceptionHandler(ResourceNotFoundException.class)
    public void notFound() {
    }

    @GetMapping("")
    public Flux<User> list(){
        return userService.list();
    }

    @GetMapping("/{id}")
    public Mono<User> getById(@PathVariable("id")final String id){
        return userService.getById(id);
    }

    @PostMapping("")
    public Flux<User> create(@RequestBody final Flux<User>  users){
        return userService.createOrUpdate(users);
    }

    @PutMapping("/{id}")
    public Mono<User> update(@PathVariable("id") final long id, @RequestBody final User user){
        Objects.requireNonNull(user);
        user.setId(id);
        return userService.createOrUpdate(user);
    }

    @DeleteMapping("/{id}")
    public Mono<User>  delete(@PathVariable("id") final String id) {
        return this.userService.delete(id);
    }
}
