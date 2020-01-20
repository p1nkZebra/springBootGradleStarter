# Spring Boot Template Project

Spring Boot Template Project is a project-starter for creating Spring Boot Java Applications.


## Installation

#### Gradle profiles

The project is build by [Gradle](https://gradle.org/) build tool.
For use different profiles use follow command.
This is example for 'dev' profile. Also 'test' and 'prod' profiles are exists.

```bash
gradlew -Pprofile=dev build > myLogs.txt 2> logErrors.txt
```
 
To create build logs files you can specify file names
```bash
gradlew -Pprofile=dev build > myLogs.txt 2> logErrors.txt
```
You can configure different profile files in '/gradle' folder 
or create a new one profile file and add logic to its use to the end of build.gradle 
```groovy
if (hasProperty('profile')) {
    apply from: rootProject.file("gradle/profile-${profile}.gradle");
    println 'Using profile file: "' + "profile-${profile}.gradle" + '" for ' + project.getName()
} else {
    // Default when no profile property is specified
    apply from: rootProject.file('gradle/profile.gradle');
    println 'Using default profile file: "' + "profile.gradle" + '" for ' + project.getName()
}
```

#### Build with Gradle
To build project you need to install Gradle on your machine or use IDE with Gradle Module 


#### Deploy WAR
For creating WAR file use 'war' Gradle task with command 
```bash
gradlew war
```
The project WAR will be created on '/build/lib' folder. This WAR file can be deploy to server (for example Apache Tomcat server)


## Usage

#### RESTful API 
There are two domain classes for testing REST API: Authors and Messages.
REST API end-points are: 
* GET /app-hello//hello 
* GET /rest-api/get-all-authors 
* GET /rest-api/get-author-by-id/{id}
* GET /rest-api/get-author-by-name/{name}
* POST /rest-api/add-author 
* PUT /rest-api/update-author/{id} 
* DELETE /rest-api/delete-author/{id}
* GET /rest-api/get-all-messages 
* GET /rest-api/get-message/{id}
* GET /rest-api/get-messages-by-author/{authorName}
* POST /rest-api/add-message
* PUT /rest-api/update-message/{id}
* DELETE /rest-api/delete-message/{id}

#### Swagger
[Swagger](https://swagger.io/) configuration is located in the com.company.springBootTemplate.config.SwaggerConfig
RestControllers for Swagger framework are located in the package com.company.springBootTemplate.controller.swagger
Each RestController has Swagger annotation like "@Api(tags={"Author REST controller"})" or "@ApiOperation("Return Author's Info from database in JSON format")"
API in browser available on URL /swagger-ui.html



#### Database configuration
The database configuration is described by the settings in the file application.properties
In-memory database H2 is used as the default database.
You can change this configuration for other database if your need.
```
spring.datasource.url=jdbc:h2:mem:h2_db;
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=sa
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

#### Database manage with Liquibase
[Liquibase](https://www.liquibase.org/) is an open-source database-independent library for tracking, managing and applying database schema changes.
Liquibase configuration in application.property file include following
```
spring.liquibase.change-log=classpath:db/changelog/liquibase-changelog.xml
spring.liquibase.default-schema=h2_schema
```


#### Spring Security Auth
The type of auth used is configured through Spring profiles in the file application.properties
```
#spring.profiles.active = basicAuth
#spring.profiles.active = databaseAuth
#spring.profiles.active = ldapAuth
spring.profiles.active = oauthAuth
```

##### HTTP Basic Auth
Basic Auth configured by WebSecurityBasicAuthConfig and Spring profile annotation @Profile("basicAuth")
Configuration includes two users:
* 'user' with password '123' (access for page '/security/any')
* 'admin' with password '456' (access for page '/security/any', 'security/admin')


##### Database Auth
Auth with using information from db_auth_user database table.
Database Auth configured by WebSecurityDatabaseAuthConfig and Spring profile annotation @Profile("databaseAuth")
Configuration includes two users:
* 'db_user' with password '123' (access for page '/security/any')
* 'db_admin' with password '456' (access for page '/security/any', 'security/admin')


##### LDAP Auth
LDAP Auth configuration is located in application.properties file and in ldap-data.ldif
```
spring.ldap.embedded.port=8389
spring.ldap.embedded.ldif=classpath:ldap-data.ldif
spring.ldap.embedded.base-dn=dc=springframework,dc=org
```

LDAP Auth configured by WebSecurityLdapAuthConfig and Spring profile annotation @Profile("ldapAuth")
Configuration includes two users:
* 'user' with password '123' (access for page '/security/any')
* 'admin' with password '456' (access for page '/security/any', 'security/admin')


##### OAuth Auth
OAuth Auth (by Google account) configuration is located in application.properties file and in ldap-data.ldif
```
security.oauth2.client.clientId=98207359239-vq2jn08cr3rkpgjajf1mf6e7h87gao97.apps.googleusercontent.com
security.oauth2.client.clientSecret=IgIY_AkseiOw1vVoz0oU7QGV
security.oauth2.client.clientAuthenticationScheme=form
security.oauth2.client.scope=openid,email,profile
security.oauth2.client.accessTokenUri=https://www.googleapis.com/oauth2/v4/token
security.oauth2.client.userAuthorizationUri=https://accounts.google.com/o/oauth2/v2/auth
security.oauth2.resource.userInfoUri=https://www.googleapis.com/oauth2/v3/userinfo
security.oauth2.resource.preferTokenInfo=true
```
OAuth Auth configured by WebSecurityOauthAuthConfig and Spring profile annotation @Profile("oauthAuth")




#### Unit and Integration tests

* unit tests with mocks (JUnit4 + Mockito in AuthorServiceTest)
* integration tests for Repositories (DataJpaTests in AuthorRepositoryTest and MessageRepositoryTest)  
* integration tests for MVC Controllers (SpringBootTests in HelloControllerTest and AuthorRestControllerTest)





