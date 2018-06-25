package com.xteam.xteamApi.security;

import com.xteam.xteamApi.domain.RedisAuthentication;
import com.xteam.xteamApi.reactiverepositories.AuthenticationRedisReactiveRepository;
import com.xteam.xteamApi.transfer.auth.TokenAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * X-Team WebFlux Authentication Service.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Slf4j
@Service
public class PasswordlessAuthenticator {

  @Autowired
  AuthenticationRedisReactiveRepository authenticationRedisReactiveRepository;

  public Mono<Boolean> authenticate(TokenAuth tokenAuth) {
    try {
      return authenticationRedisReactiveRepository.findOneByUsername(tokenAuth.getUsername())
          .flatMap(principal -> {
            if (principal != null) {
              boolean isValidName = tokenAuth.getUsername().equals(principal.getUsername()),
               isValidToken = tokenAuth.getToken().equals(principal.getToken()),
               hasExpired = !principal.hasExpired();
              return Mono.just(isValidName && isValidToken && hasExpired);
            }
            return Mono.just(false);
          });
    } catch (Exception ex) {
      log.error("Exception Encountered!");
      return Mono.just(false);
    }
  }

  public Mono<Boolean> authenticate(String username, String token) {
    try {
      return authenticationRedisReactiveRepository.findOneByUsername(username)
        .flatMap(principal -> {
          if (principal != null) {
            boolean isValidName = username.equals(principal.getUsername()),
              isValidToken = token.equals(principal.getToken()),
              hasExpired = !principal.hasExpired();
            return Mono.just(isValidName && isValidToken && hasExpired);
          }
          return Mono.just(false);
        });
    } catch (Exception ex) {
      log.error("Exception Encountered!");
      return Mono.just(false);
   }
  }

  public String generateToken(String username) {
    String token = "11111";
    try {
      authenticationRedisReactiveRepository.save(new RedisAuthentication(username, token)).log().subscribe();
    } catch (Exception ex) {
      log.error("Exception encountered generating token: " + ex);
    }
    return token;
  }

}