package com.xteam.xteamApi.reactiverepositories;

import com.xteam.xteamApi.domain.user.MongoUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * X-Team WebFlux User Repository for Reactive MongoDB.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Repository
public interface UserMongoReactiveRepository extends ReactiveMongoRepository<MongoUser, String> {

  Mono<MongoUser> findOneByUsername(String username);

  Flux<MongoUser> findAll();
}
