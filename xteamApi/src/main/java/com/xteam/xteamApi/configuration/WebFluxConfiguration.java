package com.xteam.xteamApi.configuration;

import static com.xteam.xteamApi.Constants.AUTH_LOGIN_ENDPOINT;
import static com.xteam.xteamApi.Constants.CORS_ALLOWED_ORIGINS;
import static com.xteam.xteamApi.Constants.CORS_MAX_AGE;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.resource.PathResourceResolver;
import org.springframework.web.reactive.result.view.freemarker.FreeMarkerConfigurer;

/**
 * X-Team WebFlux View and Static Asset Configuration.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Configuration
@EnableWebFlux
public class WebFluxConfiguration implements ApplicationContextAware, WebFluxConfigurer {

  private ApplicationContext context;

  /**
   * View and Freemarker related configuration beans
   */

  @Override
  public void setApplicationContext(ApplicationContext context) {
    this.context = context;
  }

  @Bean
  public FreeMarkerConfigurer freeMarkerConfigurer() {
    FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
    configurer.setPreferFileSystemAccess(false);
    configurer.setTemplateLoaderPaths("classpath:/templates/");
    configurer.setResourceLoader(this.context);
    return configurer;
  }

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    registry.freeMarker();
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/public/**")
        .addResourceLocations("classpath:/styles/", "classpath:/img/")
        .resourceChain(true)
        .addResolver(new PathResourceResolver());
  }

  /**
   * CORS configuration
   */

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**")
        .allowedOrigins(CORS_ALLOWED_ORIGINS)
        .allowedMethods("POST", "GET", "PUT", "DELETE")
        .allowCredentials(true).maxAge(CORS_MAX_AGE);

    registry.addMapping("/api/**/**")
        .allowedOrigins(CORS_ALLOWED_ORIGINS)
        .allowedMethods("POST", "GET", "PUT", "DELETE")
        .allowCredentials(true).maxAge(CORS_MAX_AGE);

    registry.addMapping("/api/**/**/**")
        .allowedOrigins(CORS_ALLOWED_ORIGINS)
        .allowedMethods("POST", "GET", "PUT", "DELETE")
        .allowCredentials(true).maxAge(CORS_MAX_AGE);

    registry.addMapping(AUTH_LOGIN_ENDPOINT)
        .allowedOrigins(CORS_ALLOWED_ORIGINS)
        .allowedMethods("POST")
        .allowCredentials(true).maxAge(CORS_MAX_AGE);

  }
}
