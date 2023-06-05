package com.jpamigration.jpamigration;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface AddressRepository extends R2dbcRepository<Address, Long> {
    Flux<Address> findAllByUserId(Long userId);
}
