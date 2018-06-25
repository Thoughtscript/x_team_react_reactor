package com.xteam.xteamApi.reactiverepositories;

import com.xteam.xteamApi.domain.email.MongoEmail;
import com.xteam.xteamApi.domain.user.MongoUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * X-Team WebFlux User Repository in Reactive Redis.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Slf4j
@Component
public class UserRedisReactiveRepository {

  @Autowired
  ReactiveRedisOperations<String, String> reactiveRedisTemplate;

  private String toStringData(MongoUser mongoUser) {
    return String.format("%s|%s|%s|%s",
        mongoUser.getUsername(),
        mongoUser.getName(),
        mongoUser.getEmail(),
        mongoUser.getPhone());
  }

  public Mono<MongoUser> findOneByUsername(String username) {
      return reactiveRedisTemplate.<String, String>opsForValue()
          .get(username)
          .log()
          .flatMap(p -> {
            String[] s = p.split("\\|");
            return Mono.just(new MongoUser(s[0], s[1], s[2], new MongoEmail(s[3]), null));
          });
  }

  public Mono<String> save(MongoUser mongoUser) {
    return reactiveRedisTemplate.<String, String>opsForValue()
        .set(mongoUser.getUsername(), toStringData(mongoUser))
        .log()
        .flatMap(p -> Mono.just(p.toString()));
  }

  public void delete(String username) {
    reactiveRedisTemplate.delete(username)
        .log()
        .block();
  }

}