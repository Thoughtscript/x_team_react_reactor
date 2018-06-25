package com.xteam.xteamApi.reactiverepositories;

import com.xteam.xteamApi.domain.RedisAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * X-Team WebFlux Authentication Repository for Reactive Redis.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Slf4j
@Component
public class AuthenticationRedisReactiveRepository  {

  @Autowired
  ReactiveRedisOperations<String, String> reactiveRedisTemplate;

  private String toStringData(RedisAuthentication redisAuthentication) {
    return String.format("%s|%s|%s",
        redisAuthentication.getUsername(),
        redisAuthentication.getToken(),
        redisAuthentication.getExpires());
  }

  public Mono<RedisAuthentication> findOneByUsername(String username) {
    return reactiveRedisTemplate.<String, RedisAuthentication>opsForValue()
        .get(username)
        .log()
        .flatMap(p -> {
          String[] s = p.split("\\|");
          log.info(s[0]);
          log.info(s[1]);
          log.info(s[2]);
          return Mono.just(new RedisAuthentication(s[0], s[1], Long.parseLong(s[2])));
        });
  }

  public Mono<String> save(RedisAuthentication redisAuthentication) {
    return reactiveRedisTemplate.<String, RedisAuthentication>opsForValue()
        .set(redisAuthentication.getUsername(), toStringData(redisAuthentication))
        .log()
        .flatMap(p -> Mono.just(p.toString()));
  }

}
