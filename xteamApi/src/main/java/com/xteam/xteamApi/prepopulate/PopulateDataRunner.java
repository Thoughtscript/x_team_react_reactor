package com.xteam.xteamApi.prepopulate;

import com.xteam.xteamApi.domain.RedisAuthentication;
import com.xteam.xteamApi.domain.email.MongoEmail;
import com.xteam.xteamApi.domain.user.MongoUser;
import com.xteam.xteamApi.reactiverepositories.AuthenticationRedisReactiveRepository;
import com.xteam.xteamApi.reactiverepositories.EmailMongoReactiveRepository;
import com.xteam.xteamApi.reactiverepositories.EmailRedisReactiveRepository;
import com.xteam.xteamApi.reactiverepositories.UserMongoReactiveRepository;
import com.xteam.xteamApi.reactiverepositories.UserRedisReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * X-Team WebFlux Command Line Runners to Prepopulate Mongo and Redis.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Slf4j
@Component
public class PopulateDataRunner implements CommandLineRunner {

  @Autowired
  EmailMongoReactiveRepository emailMongoReactiveRepository;

  @Autowired
  EmailRedisReactiveRepository emailRedisReactiveRepository;

  @Autowired
  UserMongoReactiveRepository userMongoReactiveRepository;

  @Autowired
  UserRedisReactiveRepository userRedisReactiveRepository;

  @Autowired
  AuthenticationRedisReactiveRepository authenticationRedisReactiveRepository;

  @Override
  public void run(String... args) throws Exception {
    log.info(
        "Beginning to pre-populate the database with contact and email data... waiting 20 seconds ...");

    try {
      Thread.sleep(20000);

      /**
       * Generate and Persist Emails to MongoDB.
       */

      MongoEmail emailA = new MongoEmail("email@internet.web");
      MongoEmail emailB = new MongoEmail("digitalmail@web.internet");
      MongoEmail emailC = new MongoEmail("hi@iam.example");

      log.info("Preparing to pre-populate the database with email data...");
      emailMongoReactiveRepository.saveAll(Flux.just(emailA, emailB, emailC)).subscribe();
      log.info("Verifying email persist to MongoDB...");
      emailMongoReactiveRepository.findAll().log().map(MongoEmail::getAddress).subscribe(log::info);

      /**
       * Generate and Persist Users to MongoDB.
       */

      MongoUser contactA = new MongoUser("userOne", "Jane Doe", "111-111-1111", emailA, null);
      MongoUser contactB = new MongoUser("userTwo", "John Doe", "222-222-2222", emailB, null);
      MongoUser contactC = new MongoUser("userThree", "Every Person", "333-333-3333", emailC, null);

      log.info("Preparing to pre-populate the database with user data...");
      userMongoReactiveRepository.saveAll(Flux.just(contactA, contactB, contactC)).subscribe();
      log.info("Verifying user persist to MongoDB...");
      userMongoReactiveRepository.findAll().log().map(MongoUser::getUsername).subscribe(log::info);

      /**
       * Generate and Persist Emails to Redis.
       */

      log.info("Preparing to cache the database with email data...");
      emailRedisReactiveRepository.save(emailA).subscribe();
      emailRedisReactiveRepository.save(emailB).subscribe();
      emailRedisReactiveRepository.save(emailC).subscribe();

      Thread.sleep(5000);

      log.info("Verifying email persist to Redis...");
      emailRedisReactiveRepository.findByAddress(emailA.getAddress()).subscribe();
      emailRedisReactiveRepository.findByAddress(emailB.getAddress()).subscribe();
      emailRedisReactiveRepository.findByAddress(emailC.getAddress()).subscribe();

      /**
       * Generate and Persist Users to Redis.
       */

      log.info("Preparing to cache the database with user data...");
      userRedisReactiveRepository.save(contactA).subscribe();
      userRedisReactiveRepository.save(contactB).subscribe();
      userRedisReactiveRepository.save(contactC).subscribe();

      Thread.sleep(5000);

      log.info("Verifying user persist to Redis...");
      userRedisReactiveRepository.findOneByUsername(contactA.getUsername()).subscribe();
      userRedisReactiveRepository.findOneByUsername(contactB.getUsername()).subscribe();
      userRedisReactiveRepository.findOneByUsername(contactC.getUsername()).subscribe();

      /**
       * Generate Default Authentication.
       */

      RedisAuthentication redisAuthenticationXiu = new RedisAuthentication("xiu", "22222");
      RedisAuthentication redisAuthenticationSolomon = new RedisAuthentication("solomon", "33333");

      authenticationRedisReactiveRepository.save(redisAuthenticationXiu).subscribe();
      authenticationRedisReactiveRepository.save(redisAuthenticationSolomon).subscribe();
      log.info("Verifying authentication persist to Redis...");
      Thread.sleep(5000);

      authenticationRedisReactiveRepository.findOneByUsername(redisAuthenticationXiu.getUsername()).subscribe();
      authenticationRedisReactiveRepository.findOneByUsername(redisAuthenticationSolomon.getUsername()).subscribe();

    } catch (Exception ex) {
      log.error("Exception pre-populating database with contact and email data: " + ex);
      ex.printStackTrace();
    } finally {
      log.info("Pre-population of database complete!");
    }

  }

}
