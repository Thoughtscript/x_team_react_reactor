package com.xteam.xteamApi.transfer.response;

import com.xteam.xteamApi.domain.user.MongoUser;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * X-Team WebFlux Custom Response DTO to Cleanly Abstract and Deserialize All Mono and Flux Responses.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Getter
@Setter
public class CustomResponse {

  private List<MongoUser> users;
  private List<String> contacts;
  private String message;

  /**
   * List constructor for minimal instantiation.
   */
  public CustomResponse(MongoUser mongoUser, String message) {
    this.contacts = new ArrayList<>();
    this.users = (mongoUser != null) ? new ArrayList<MongoUser>() {{
      add(mongoUser);
    }} : new ArrayList<>();
    this.message = message;
  }

  /**
   * Simple constructor for minimal instantiation (list).
   */
  public CustomResponse(List<MongoUser> users, String message) {
    this.contacts = new ArrayList<>();
    this.users = (users != null) ? users : new ArrayList<>();
    this.message = message;
  }

  /**
   * Full constructor.
   */
  public CustomResponse(List<MongoUser> users, List<String> contacts, String message) {
    this.users = (users != null) ? users : new ArrayList<>();
    this.contacts = (contacts != null) ? contacts : new ArrayList<>();
    this.message = message;
  }

  /**
   * Simple helper to add a MongoUser to a pre-existing instance.
   */
  public void addUser(MongoUser mongoUser) {
    if (this.users != null) {
      this.users.add(mongoUser);
    } else {
      new ArrayList<MongoUser>() {{
        add(mongoUser);
      }};
    }
  }

  /**
   * Simple helper to add an email to contacts list.
   */
  public void addEmail(String email) {
    if (this.contacts != null) {
      this.contacts.add(email);
    } else {
      new ArrayList<String>() {{
        add(email);
      }};
    }
  }

}
