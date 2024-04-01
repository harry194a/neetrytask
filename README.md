# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/#build-image)
* [Docker Reference Guide](https://docs.docker.com)

### Application build/run description

1. Go to the project base directory f.e cd {base-dir}/neetryTask
2. Make "mvn clean install"
3. Make "docker-compose -f iam/infrastructure/src/main/docker/docker-compose.yml up"
4. Make "docker-compose -f notes/src/main/docker/docker-compose.yml up"

### Default users

password: 12345678
userName: testAdmin@gmail.com

password: 12345678
userName: testuser1@gmail.com

### OAUTH API Description

* [swagger url](http://localhost:8081/swagger-ui/index.html#/)

* [user registration]

          curl -X POST \
            http://localhost:8081/oauth/user/register \
            -H 'content-type: application/json' \
            -d '{
            "email": {email},
            "password": {password},
            "firstName": {firstName},
            "lastName": {string},
            "phone": {phone},
            "address": {address}}'


* [user info]

          curl -X GET \
            http://localhost:8081/oauth/accounts/user-info \
            -H 'authorization: Bearer {access_token}' \
            -H 'content-type: application/json'


* [jwt access token]

         curl -X POST \
           http://localhost:8081/oauth/token \
           -H 'content-type: application/json' \
           -d '{
           "userName": {email},
           "password": {password}
         }'

### NOTES API Description

* [swagger url](http://localhost:8080/swagger-ui.html)

* [Create]

        curl -X POST \
          http://localhost:8080/api/books \
          -H 'authorization: Bearer {access_token}' \
          -H 'content-type: application/json' \
          -d '{
          "title": {title}
          "author": {author},
          "genre": {genre},
          "description": {description},
          "isbn": {isbn},
          "image": {image},
          "publish": {publish},
          "publisher": {publisher}
        }'


* [Update]

        curl -X PUT \
          http://localhost:8080/api/books/{id} \
          -H 'authorization: Bearer {access_token}' \
          -H 'content-type: application/json' \
          -d '{
          "title": {title}
          "author": {author},
          "genre": {genre},
          "description": {description},
          "isbn": {isbn},
          "image": {image},
          "publish": {publish},
          "publisher": {publisher}
        }'   


* [Get]

        curl -X GET \
          http://localhost:8080/api/books/{id} \
          -H 'authorization: Bearer {access_token}' \
          -H 'content-type: application/json'  


* [Delete]

        curl -X DELETE \
          http://localhost:8080/api/books/{id} \
          -H 'authorization: Bearer {access_token}' \
          -H 'content-type: application/json'  


* [Search]

        curl -X GET \
          'http://localhost:8080/api/books/search?from={from(optional)}&size={size(optional)}}' \
          -H 'authorization: Bearer {access_token}' \
          -H 'cache-control: no-cache' \
          -H 'content-type: application/json'
