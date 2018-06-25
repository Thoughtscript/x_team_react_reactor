# x_team_react_reactor

The contents of this exploratory project include:

1. A [React 16.x.x](https://reactjs.org/), [React Router 4.x.x](https://github.com/ReactTraining/react-router), [Redux](https://github.com/reactjs/react-redux) client.
2. Java [Spring Boot 2.x.x App](https://spring.io/blog/2018/03/01/spring-boot-2-0-goes-ga) leveraging both [Reactive WebFlux](https://docs.spring.io/spring/docs/5.0.0.BUILD-SNAPSHOT/spring-framework-reference/html/web-reactive.html) and routing implemented using the new **Functional Programming** *RouterFunction*.
3. [Spring Reactive MongoDB](https://www.mongodb.com/) along with [Spring Data Redis](https://redis.io/) for caching.
4. [Docker](https://www.docker.com/) and [Docker Compose](https://docs.docker.com/compose/) to simplify setup.
5. [Passwordless Security](https://www.sitepoint.com/lets-kill-the-password-magic-login-links-to-the-rescue/) via email provided by [Sendgrid](https://sendgrid.com/).
6. [WebPack](https://webpack.js.org/) build tools for the client and good ole [Maven](https://maven.apache.org/install.html) for the Java!
7. [Project Lombok](https://projectlombok.org/) to help improve code readability!

For more specification and versioning details please consult the relevant `package.json`, `dockerfile`, and `pom.xml` files!

Please note that security settings (credentials, etc.) exist as examples and have been disabled.

# Installation and Setup

Manual options are available and documented in each sub-project.

You will need to acquire Sendgrid credentials and add them to the WebFlux `application.yml`. 

Then you will need to run `$ mvn install` within the `x_team_react_reactor/xteamApi` directory. 
Move the compiled `.jar` into `x_team_react_reactor/xteamApi` or modify the start scripts.

Otherwise please run the provided `docker-compose.yml` to launch the entire project:

```bash
	$ cd docker
	$ docker-compose up
	$ docker-compose down
```

Alternatively,
```bash
  $ cd docker
  $ alt-docker-compose-api.sh
  $ alt-docker-compose-client.sh
```

# React Endpoints

![Image](https://github.com/Thoughtscript/x_team_react_reactor/blob/master/assets/ezgif-5-c61d6da46f.gif)

# Server Endpoints

![api](https://github.com/Thoughtscript/x_team_react_reactor/blob/master/assets/api_landing_page.PNG)

# Passwordless Security Flow

![chart](https://github.com/Thoughtscript/x_team_react_reactor/blob/master/assets/AuthFlow.PNG)

Video of the same:

![login](https://github.com/Thoughtscript/x_team_react_reactor/blob/master/assets/email.gif)

# Some Potentially Annoying Gotya's!

<h3><span style="color:red">Docker and MongoDB</span></h3>

If run into an issue with MongoDB not running correctly in your container (which may or may not give an `Exit Code 100` à la <a href="https://github.com/docker-library/mongo/issues/18">**Issue #18**</a>), you might be able to resolve your issue by "factory resetting" your Docker install (which is what happened to me and how I solved it). I hope that saves you some time and stress `<3`!

I also wanted to go over a few things I encountered that I hope will reduce your stressors and help you to be continually awesome!

<h3><span style="color:red">Reactive Spring Redis</span></h3>

**This one got me bad ->** You might encounter exceptions when combining the newest versions **Lombok**, **Spring Boot**, and **Jackson**:

1. https://github.com/rzwitserloot/lombok/issues/1563
2. https://github.com/rzwitserloot/lombok/issues/1677
3. https://github.com/spring-projects/spring-boot/issues/12568
4. https://stackoverflow.com/questions/48330613/objectmapper-cant-deserialize-without-default-constructor-after-upgrade-to-spri

There are many good resources out there that advise modifying your **Lombok** configuration when using `1.16.18+` like so:
```
//lombok.config
lombok.anyConstructor.addConstructorProperties=true
```

Such a configuration may help you to resolve any such conflicts. For me, it took a couple more steps to jigger up a sufficiently functional example (and one that could some use more work to help improve it all up - open source team-work, anyone)?

I dug around and found what seem to be a few conflicting resources (to the extent that <a href="https://hyperallergic.com/60500/momas-hilariously-bizarre-silent-screams/">**this**</a> pretty much summed up my feelings on the matter hehe). For example, I discovered <a href="https://jira.spring.io/browse/DATAREDIS-831">**this**</a> ticket over on the **Spring.io** JIRA page. It appears that *reactive* Redis support is purely experimental at this point (and you can find a <a href="https://github.com/spring-projects/spring-boot/tree/master/spring-boot-project/spring-boot-starters/spring-boot-starter-data-redis-reactive">**Reactive Redis Starter**</a> that's nearly empty on first blush). Hilariously, and conversely, the same example has several unit tests that dispense with `Hash` driven `ReactiveRedisOperations` in favor of `ReactiveRedisOperations<String, String>` if you dig around a bit. I was able to get things working using `Strings`, thereby.

So, my advice is to either use `ReactiveRedisOperations<String, String>` or attempt to jigger up a solution combining the native <a href="https://fasterxml.github.io/jackson-databind/javadoc/2.7/com/fasterxml/jackson/databind/ObjectMapper.html">**Jackson Databind**</a> `ObjectMapper` and the apparently partially functional <a href="https://github.com/spring-projects/spring-boot/tree/master/spring-boot-project/spring-boot-starters/spring-boot-starter-data-redis-reactive">**Reactive Redis Starter**</a> (for more on this see <a href="https://spring.io/blog/2016/11/28/going-reactive-with-spring-data">**the official docs**</a>.)!

If you have some advice about better ways to improve the serialization of cached Redis data, feel free to send me a message over at <a href="emailto:adam.gerard@x-team.com">**adam.gerard@x-team.com**</a> and I'll happily credit you (and would even more happily be interested in expanding this example)!

<h3><span style="color:red">Freemarker</span></h3>

**Spring Boot 2.x.x WebFlux** also does not support the use of **JSP** or **JSTL** (or at least very easily). Instead, it's recommended that you configure your views using <a href="https://freemarker.apache.org/index.html">**Freemarker**</a> which can be cleanly and easily set up as follows:

```java
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
```

<h3><span style="color:red">Oauth2</span></h3>

There is limited but increasing support for Oauth2 in WebFlux: [Issue #4807](https://github.com/spring-projects/spring-security/issues/4807) and the [Official Docs](https://docs.spring.io/spring-boot/docs/2.0.0.BUILD-SNAPSHOT/reference/html/boot-features-security-webflux.html).

This concern motivated a passwordless security approach!

<h3><span style="color:red">HttpServletRequest</span></h3>

**WebFlux** Reactive Controllers do not support `HttpServletRequest` out of the box:

```java
private HttpServletRequest hsrFromShr(ServerHttpRequest request) {
    return ((ServletServerHttpRequest) request).getServletRequest();
}
```
This can result in a snag when leveraging Auth0 or other Oauth2 Java libraries. Using the above code can get you around that easily. 

## Future Plans

There are at least a couple other ways to improve on this example:

1. To incorporate **SMS** text authentication using associated <a href="https://en.wikipedia.org/wiki/SMS_gateway">**phone-number SMS gateways**</a>!
2. To leverage a random-seed with `nonce` and **IP Address** to go one step further and **obviate the need for a magic email** (or at least provide the option). There would be no manually entered information required.

On **2**, every time a user session begins, a randomly generated, hashed (or salted), `String` with **padding**, a time-dependent `nonce`, and **IP Address** could be generated. That value would then be sent via POST right over to the server, and cached after decryption into a new token. That new token would then be returned which could be used to authenticate directly and which would contain the `nonce` and **IP Address** values.

That's just a rough-sketch and I hope there are some better ways to go about it.

# Resources and Shout-Outs

1. <a href="https://snazzymaps.com/style/1371/purple">**nathandell**</a> on <a href="https://snazzymaps.com/style/1371/purple">**Snazzy Maps**</a>

2. <a href="https://uigradients.com/#SublimeLight">**uiGradients**</a>

3. <a href="https://unsplash.com/photos/OrwkD-iWgqg">**Meiying Ng**</a> by way of <a href="https://unsplash.com">**Unsplash**</a>

4.  **Creactiviti's** in-memory example available <a href="https://github.com/creactiviti/spring-security-passwordless/blob/master/src/main/resources/templates/signin.html">**here**</a>

5. <a href="https://unsplash.com/photos/RPI-SpV7PL8">**Carles Rabada**</a>

6. <a href="https://github.com/gracefulife/Spring-Webflux-study">**Helpful Reactive Template Example**</a>

7. <a href="https://unsplash.com/photos/8NHL3OI5eWc">**Austin Chan**</a>

# Licensing and Use

MIT licensed. Oh yeah.
