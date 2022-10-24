# AuthService 
this is a jwt authentication service, made for [tenpo](tenpo.cl) evaluation.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Security](https://spring.io/projects/spring-security)

# Requirements
For building and running the application you need:
 - JDK 17
 - Maven 
 - Docker (optional)

# Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the cl.tenpo.evaluation.authservice.AuthServiceApplication class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:

mvn spring-boot:run

# Deploying the application with docker
1) Should build the image with following cmd
`` docker build . -t tenpo-auth-service:1.0.0-beta``

2) List images
   `` docker image ls``
3) Run the image ``docker run -p 8080:8080 tenpo-auth-service:1.0.0-beta``

# How to use?
1) Register an user
```
curl --location --request POST 'http://{{uri}}/api/v1/auth/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
  "email": "tenpo@tenpo.cl",
  "username": "user",
  "password": "user1234",
  "roleList": [
    "ROLE_USER"
  ]
}
```
2) Obtain JWT token
```
curl --location --request POST 'http://{{uri}}/api/v1/auth/signin' \
--header 'Content-Type: application/json' \
--data-raw '{
"username": "admin",
"password": "nachonach"
}
```
### Response interface
```
{
  "sub": "admin",
  "authorities": [
    "ROLE_USER"
  ],
  "iat": 1666652493,
  "exp": 1666656093
} 
```

3) Validate each request of another microservice with this one, and could save it as an springboot user principal !

# Contributing
Bug reports and pull requests are welcome :)
