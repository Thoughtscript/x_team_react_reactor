package com.xteam.xteamApi.transfer.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * X-Team WebFlux Authentication DTO to generate MagicLink.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Getter
@Setter
public class ForMagicLink {
  private String username;
  private String email;
}
