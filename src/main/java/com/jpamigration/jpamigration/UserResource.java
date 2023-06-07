package com.jpamigration.jpamigration;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public Flux<User> getUsers() {
        return userService.findAll();
    }

    @PostMapping("/api/users")
    public Mono<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/api/users/{id}")
    public Mono<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @PostMapping("/api/users/{id}/address")
    public Mono<Address> addAddress(@PathVariable("id") Long id, @RequestBody Address address) {
        return userService.addAddress(id, address);
    }

    @DeleteMapping("/api/users/{id}")
    public Mono<Void> deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }
}
