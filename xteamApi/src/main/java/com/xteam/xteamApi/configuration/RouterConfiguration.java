package com.xteam.xteamApi.configuration;

import static com.xteam.xteamApi.Constants.API_ROUTER_USER_ALL;
import static com.xteam.xteamApi.Constants.API_ROUTER_USER_NEW;
import static com.xteam.xteamApi.Constants.API_ROUTER_USER_ONE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.xteam.xteamApi.services.FunctionalRouterWebHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * X-Team WebFlux Functional and Public API.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Configuration
public class RouterConfiguration {

  @Bean
  public FunctionalRouterWebHandler handler() {
    return new FunctionalRouterWebHandler();
  }

  @Bean
  @Autowired
  public RouterFunction<ServerResponse> routes(FunctionalRouterWebHandler handler) {
    return route(POST(API_ROUTER_USER_ONE).and(accept(APPLICATION_JSON)), handler::getOneUser)
        .andRoute(POST(API_ROUTER_USER_NEW).and(accept(APPLICATION_JSON)), handler::saveOneUser)
        .andRoute(DELETE(API_ROUTER_USER_ONE).and(accept(APPLICATION_JSON)), handler::deleteOneUser)
        .andRoute(PUT(API_ROUTER_USER_ONE).and(accept(APPLICATION_JSON)), handler::updateOneUser)
        .andRoute(GET(API_ROUTER_USER_ALL).and(accept(APPLICATION_JSON)), handler::getAllUsers);
  }

}