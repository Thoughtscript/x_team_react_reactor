package com.xteam.xteamApi.controllers;

import static com.xteam.xteamApi.Constants.AUTH_LOGIN_ENDPOINT;

import com.xteam.xteamApi.email.SendGridEmail;
import com.xteam.xteamApi.security.PasswordlessAuthenticator;
import com.xteam.xteamApi.transfer.auth.ForMagicLink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * X-Team WebFlux Authentication Endpoint to Send Magic Link.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Slf4j
@RestController
public class PasswordlessReactiveRedisController {

  @Autowired
  SendGridEmail sendGridEmail;

  @Autowired
  PasswordlessAuthenticator passwordlessAuthenticator;

  @PostMapping(AUTH_LOGIN_ENDPOINT)
  public void generateMagicLink(@RequestBody ForMagicLink forMagicLink) {
    try {
      String username = forMagicLink.getUsername();
      String email = forMagicLink.getEmail();
      String token = passwordlessAuthenticator.generateToken(username);
      sendGridEmail.sendMagicEmail(email, username, token);
    } catch (Exception ex) {
      log.error("Exception sending magic email: " + ex);
    }
  }

}
