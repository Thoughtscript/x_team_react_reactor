package com.xteam.xteamApi.domain;

import com.xteam.xteamApi.Constants;
import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

/**
 * X-Team WebFlux Authentication Entity for Redis Caching.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Slf4j
@Data
@Getter
@Setter
public class RedisAuthentication {

  @Id
  private String username;
  private String token;
  private long expires;

  public RedisAuthentication(String username, String token) {
    this.username = username;
    this.token = token;
    this.expires = new Date().getTime() + Constants.FIFTEEN_MINS;
  }

  //Only Use When Deserializing From Redis String
  public RedisAuthentication(String username, String token, long expires) {
    this.username = username;
    this.token = token;
    this.expires = expires;
  }

  public boolean hasExpired() {
    log.info("Checking if token has expired!");
    return this.expires > new Date().getTime() + Constants.FIFTEEN_MINS;
  }

}
