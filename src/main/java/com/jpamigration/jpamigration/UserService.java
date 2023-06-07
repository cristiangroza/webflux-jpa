package com.jpamigration.jpamigration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Flux<User> findAll() {
        logger.debug("Info thread");
        return userRepository.findAll()
                .doOnNext(next -> logger.info("{}", next));
    }

    public Mono<User> createUser(User user) {
        logger.debug("Info thread");
        return userRepository.save(user)
                .doOnNext(savedUser -> logger.info("Created user: {}", savedUser));
    }

    public Mono<Address> addAddress(Long id, Address address) {
        logger.debug("Info thread");
        return userRepository.findById(id)
                .map(user -> user.addAddress(address))
                .flatMap(userRepository::save)
                .thenReturn(address);
    }

    public Mono<User> updateUser(Long id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setName(user.getName());
                    return existingUser;
                })
                .flatMap(userRepository::save);
    }

    public Mono<Void> deleteUser(Long id) {
        return userRepository.deleteById(id);
    }
}
