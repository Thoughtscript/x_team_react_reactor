package com.xteam.xteamApi.transfer.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * X-Team WebFlux Authentication DTO for Updating Information.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Getter
@Setter
public class AuthenticatedUpdate extends TokenAuth {
  private String newUsername;
  private String newName;
  private String newPhone;
  private String newEmail;
}
