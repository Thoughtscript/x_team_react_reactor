package com.xteam.xteamApi.email;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.xteam.xteamApi.Constants;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * X-Team WebFlux SendGrid Email Configuration and Helpers.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Slf4j
@Component
public class SendGridEmail {

  @Value("${sendgrid.email.from}")
  private String fromProp;

  @Value("${sendgrid.email.subject}")
  private String subjectProp;

  @Value("${sendgrid.api.key}")
  private String apiKeyProp;

  public void sendMagicEmail (String email, String username, String token) {

    try {

    Mail mail = new Mail(
        new Email(fromProp),
        subjectProp,
        new Email(email),
        new Content("text/plain",
            String.format("%s %s?token=%s&username=%s",
            Constants.EMAIL_MAGIC_LINK_GREETING,
            Constants.AUTH_LOGIN_ENDPOINT_FULLY_QUALIFIED,
            token,
            username))
    );

    SendGrid sg = new SendGrid(apiKeyProp);
    Request request = new Request();

      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      log.debug(sg.api(request).toString());

    } catch (IOException ex) {
      log.error("Exception encountered sending Send Grid email: " + ex);
    }
  }

}
