package com.xteam.xteamApi.reactiverepositories;

import com.xteam.xteamApi.domain.email.MongoEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * X-Team WebFlux Email Repository for Reactive Redis.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Slf4j
@Component
public class EmailRedisReactiveRepository {

  @Autowired
  ReactiveRedisOperations<String, String> reactiveRedisTemplate;

  public Mono<MongoEmail> findByAddress(String address) {
    return reactiveRedisTemplate.<String, String>opsForValue()
        .get(address)
        .log()
        .map(p -> new MongoEmail(p));
  }

  public Mono<String> save(MongoEmail mongoEmail) {
    return reactiveRedisTemplate.<String, String>opsForValue()
        .set(mongoEmail.getAddress(), mongoEmail.getAddress())
        .log()
        .map(p -> p.toString());
  }

  public void delete(String address) {
    reactiveRedisTemplate.delete(address)
        .log()
        .blockOptional();
  }

}