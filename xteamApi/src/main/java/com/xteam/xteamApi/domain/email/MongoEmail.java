package com.xteam.xteamApi.domain.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * X-Team WebFlux Email Entity for Mongo.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Getter
@Setter
@Document(collection = "emails")
public class MongoEmail {

  @Id
  private String address;

  public MongoEmail(String address) {
    this.address = address;
  }

}