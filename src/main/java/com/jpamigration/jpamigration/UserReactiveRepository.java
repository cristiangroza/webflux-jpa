package com.jpamigration.jpamigration;

import com.jpamigration.jpamigration.utils.BlockingOpsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

@Repository
public class UserReactiveRepository {
    private final Logger logger = LoggerFactory.getLogger(UserReactiveRepository.class);

    private final UserJpaRepository jpaUserRepository;
    private final TransactionTemplate transactionTemplate;

    public UserReactiveRepository(UserJpaRepository jpaUserRepository, TransactionTemplate transactionTemplate) {
        this.jpaUserRepository = jpaUserRepository;
        this.transactionTemplate = transactionTemplate;
    }

    public Flux<User> findAll() {

        return BlockingOpsUtils.executeToFlux(() -> transactionTemplate.execute(transactionStatus -> jpaUserRepository.findAllBy()
                .map(user -> user.loadOrders().loadAddresses())
                .toList()));
    }

    public Mono<User> save(User user) {
        return BlockingOpsUtils.executeToMono(() -> jpaUserRepository.save(user));
    }

    public Mono<User> findById(Long id) {
        return BlockingOpsUtils.executeToMono(() -> transactionTemplate.execute(transactionStatus -> jpaUserRepository.findById(id)
                .map(user -> user.loadOrders().loadAddresses())
                .orElse(null)
        ));
    }

    public Mono<Void> deleteById(Long id) {
        return BlockingOpsUtils.execute(() -> {
            logger.info("Blocking operation execute");
            jpaUserRepository.deleteById(id);
        });
    }
}

@Repository
interface UserJpaRepository extends CrudRepository<User, Long> {
    Stream<User> findAllBy();
}
