package com.jpamigration.jpamigration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepostitory userRepostitory;
    private final AddressRepository addressRepository;


    public UserService(UserRepostitory userRepostitory, AddressRepository addressRepository) {
        this.userRepostitory = userRepostitory;
        this.addressRepository = addressRepository;
    }


    public Flux<User> findAll() {
        logger.info("Find ALL");
        return userRepostitory.findAll()
                .concatMap(user -> addressRepository.findAllByUserId(user.getId())
                        .collectList()
                        .map(list -> {
                            user.setAddresses(list);
                            return user;
                        })
                );
    }
}
