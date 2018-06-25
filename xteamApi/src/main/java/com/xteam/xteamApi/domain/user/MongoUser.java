package com.xteam.xteamApi.domain.user;

import com.xteam.xteamApi.domain.email.MongoEmail;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * X-Team WebFlux User Entity for Mongo.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */


@Getter
@Setter
@Document(collection = "users")
public class MongoUser {

  @Id
  private String username;
  private String name;
  private String phone;

  @DBRef
  @Field("contacts")
  private List<MongoEmail> contacts;

  @DBRef
  @Field("email")
  private MongoEmail email;

  /**
   * Full constructor.
   * @param username
   * @param name
   * @param phone
   * @param email
   * @param contacts
   */
  public MongoUser(String username, String name, String phone, MongoEmail email, List<MongoEmail> contacts) {
    this.username = username;
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.contacts = (contacts != null) ? contacts : new ArrayList<>();
  }

  /**
   * Simple helper to add an email to contacts list.
   */
  public void addContact(String email) {
    if (this.contacts != null) this.contacts.add(new MongoEmail(email));
    else new ArrayList<String>() {{
        add(email);
      }};
    }

  public MongoUser update(String name, String phone, String address, List<MongoEmail> contacts) {
    if (name != null) this.name = name;
    if (phone != null) this.phone = phone;
    if (address != null) this.email = new MongoEmail(address);
    if (contacts != null) this.contacts = contacts;
    return this;
  }

}