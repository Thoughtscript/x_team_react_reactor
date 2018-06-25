# Docker Compose Readme

Four core microservices:

(1) An Oauth2-protected Java 8 Spring Boot 2.0.0 WebFlux Server.  
(2) A React client.  
(2) A MongoDB cluster launched via Docker.  
(3) A Redis cluster for temporary caching.  

### Bash

(1) Bash scripts with one liner Docker commands are provided for simple deployment of the DB's.
(2) Bash scripts are provided as an alternative to Docker that will run everything:

```bash
	$ alt-docker-compose-api.sh
	$ alt-docker-compose-client.sh
```

For more see the links below!

### Resources

MongoDB: https://github.com/dockerfile/mongodb

Redis: https://github.com/dockerfile/redis