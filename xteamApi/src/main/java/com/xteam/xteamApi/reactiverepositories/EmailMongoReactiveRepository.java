package com.xteam.xteamApi.reactiverepositories;

import com.xteam.xteamApi.domain.email.MongoEmail;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * X-Team WebFlux Email Repository for Reactive Mongo DB.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Repository
public interface EmailMongoReactiveRepository extends ReactiveMongoRepository<MongoEmail, String> {

  Mono<MongoEmail> findOneByAddress(String address);

  Flux<MongoEmail> findAll();
}