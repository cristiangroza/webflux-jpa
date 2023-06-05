package com.jpamigration.jpamigration;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface UserRepostitory extends R2dbcRepository<User, Long> {
}
