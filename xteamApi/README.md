# Java 8 Spring Boot Spring 2.0.0 WebFlux Readme

This the the Java WebFlux API for X-Team.

Included herein is:

1. A `Dockerfile` to simplify deployment (either standalone or as part of an entirely orchestrated system)
2. A RESTful Reactive Web example with both functional and reactive implementations.
3. Passwordless authentication on WebFlux security.
4. Support for Redis caching and MongoDB.

# Authentication Details

1. The user will issue a post against the authentication controller.    
2. The user will be notified, clientside, that an email has been sent!  
3. The email contains a link with a token valid for 15 minutes.  
4. That link, when clicked, will redirect the user to a second route in the client that will issue a POST against the secured API endpoint.    
5. It then verifies the token and presents the correct data.  

***Please note that this is NOT production-worthy as is. This is a proof of concept.***

Per the comment above, here are some steps that *would* make the system presented *more* production-worthy:

1.	SSL to assist with encrypting requests.
2.	Nonce, padded, token encryption in WebFlux so that any token being handled client-side exists in perfect secrecy and is truly valid for 15 minutes only.
3.	MongoDB and Redis encryption. Redis is used to handle tokens volitely. Tokens are never stored in MongoDB.

# Standalone Docker

```bash
  $ sudo docker build - < Dockerfile
  $ sudo docker run [image_name] 
```

# Protected Endpoints

### Get One User - localhost:8080/api/flux/user/one - POST
```json
{
	"token": "33333",
	"username": "solomon",
	"id": "userOne"
}
```
```json
{
    "users": [
        {
            "username": "userOne",
            "name": "Jane Doe",
            "phone": "com.xteam.xteamApi.domain.email.MongoEmail@756aadfc",
            "contacts": [],
            "email": {
                "address": "111-111-1111"
            }
        }
    ],
    "contacts": [],
    "message": "Success!"
}
```

### Create New User - localhost:8080/api/flux/user/one/new - POST
```json
{
	"token": "33333",
	"username": "solomon",
	"newUsername": "randomPerson",
	"newName": "Sally Joe",
	"newPhone": "432-432-1322",
	"newEmail": "randomPerson@gmail.com"
}
```
```json
{
    "users": [
        {
            "username": "randomPerson",
            "name": "Sally Joe",
            "phone": "432-432-1322",
            "contacts": [],
            "email": {
                "address": "randomPerson@gmail.com"
            }
        }
    ],
    "contacts": [],
    "message": "Success!"
}
```

### Delete New User - localhost:8080/api/flux/user/one - DELETE
```json
{
	"token": "33333",
	"username": "solomon",
	"id": "userOne"
}
```
```json
{
    "users": [
        {
            "username": "userOne",
            "name": null,
            "phone": null,
            "contacts": [],
            "email": null
        }
    ],
    "contacts": [],
    "message": "Success!"
}
```

### Update New User - localhost:8080/api/flux/user/one - PUT
```json
{
	"token": "33333",
	"username": "solomon",
	"newUsername": "randomPerson",
	"newName": "Sally Joe",
	"newPhone": "432-432-1322",
	"newEmail": "randomPerson@gmail.com"
}
```
```json
{
    "users": [
        {
            "username": "randomPerson",
            "name": "Sally Joe",
            "phone": "432-432-1322",
            "contacts": [],
            "email": {
                "address": "randomPerson@gmail.com"
            }
        }
    ],
    "contacts": [],
    "message": "Success!"
}
```

###  Get All Users - localhost:8080/api/flux/user/all - POST
```json
{
	"token": "33333",
	"username": "solomon"
}
```
```json
{
    "users": [
        {
            "username": "userTwo",
            "name": "John Doe",
            "phone": "222-222-2222",
            "contacts": [],
            "email": {
                "address": "digitalmail@web.internet"
            }
        },
        {
            "username": "userThree",
            "name": "Every Person",
            "phone": "333-333-3333",
            "contacts": [],
            "email": {
                "address": "hi@iam.example"
            }
        },
        {
            "username": "randomPerson",
            "name": "Sally Joe",
            "phone": "432-432-1322",
            "contacts": [],
            "email": {
                "address": "randomPerson@gmail.com"
            }
        }
    ],
    "contacts": [],
    "message": "Success!"
}
```

# Public Endpoints

### Get One User - localhost:8080/api/functional/user/one - POST
```json
{
	"id": "userOne"
}
```
```json
{
    "users": [
        {
            "username": "userOne",
            "name": "Jane Doe",
            "phone": "com.xteam.xteamApi.domain.email.MongoEmail@72503b19",
            "contacts": [],
            "email": {
                "address": "111-111-1111"
            }
        }
    ],
    "contacts": [],
    "message": "Success!"
}
```

### Create One User - localhost:8080/api/functional/user/new - POST
```json
{
	"username": "randomPerson",
	"name": "Sally Joe",
	"phone": "432-432-1322",
	"email": "randomPerson@gmail.com"
}
```
```json
{
    "users": [
        {
            "username": "randomPerson",
            "name": "Sally Joe",
            "phone": "432-432-1322",
            "contacts": [],
            "email": {
                "address": "randomPerson@gmail.com"
            }
        }
    ],
    "contacts": [],
    "message": "Success!"
}
```

### Delete One User - localhost:8080/api/functional/user/one - DELETE
```json
{
	"id": "userOne"
}
```
```json
{
    "users": [
        {
            "username": "userOne",
            "name": null,
            "phone": null,
            "contacts": [],
            "email": null
        }
    ],
    "contacts": [],
    "message": "Success!"
}
```

### Update One User - localhost:8080/api/functional/user/one - PUT
```json
{
	"username": "randomPerson",
	"name": "Sally Joe",
	"phone": "432-432-1322",
	"email": "randomPerson@gmail.com"
}
```
```json
{
    "users": [
        {
            "username": "randomPerson",
            "name": "randomPerson",
            "phone": "Sally Joe",
            "contacts": [],
            "email": {
                "address": "432-432-1322"
            }
        }
    ],
    "contacts": [],
    "message": "Success!"
}
```

### Get All Users localhost:8080/api/functional/user/all - GET
```json
{}
```
```json
{
    "users": [
        {
            "username": "userTwo",
            "name": "John Doe",
            "phone": "222-222-2222",
            "contacts": [],
            "email": {
                "address": "digitalmail@web.internet"
            }
        },
        {
            "username": "userThree",
            "name": "Every Person",
            "phone": "333-333-3333",
            "contacts": [],
            "email": {
                "address": "hi@iam.example"
            }
        },
        {
            "username": "randomPerson",
            "name": "Sally Joe",
            "phone": "432-432-1322",
            "contacts": [],
            "email": {
                "address": "randomPerson@gmail.com"
            }
        },
        {
            "username": "userOne",
            "name": "Jane Doe",
            "phone": "111-111-1111",
            "contacts": [],
            "email": {
                "address": "email@internet.web"
            }
        }
    ],
    "contacts": [],
    "message": "Success!"
}
```

# Shout Outs

1. [Helpful Reactive Template Example](https://github.com/gracefulife/Spring-Webflux-study)
2. [Austin Chan](https://unsplash.com/photos/8NHL3OI5eWc)
3. [E4developer] (https://www.e4developer.com/2018/04/11/getting-reactive-with-spring-boot-2-0-and-reactor/)