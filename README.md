# 스프링 시큐리티 기본 Version.1

### MySQL DB 및 사용자 생성

```sql
create user 'cos'@'%' identified by 'cos1234';
GRANT ALL PRIVILEGES ON *.* TO 'cos'@'%';
create database security;
use security;
```

### SecurityConfig.java 권한 설정 방법

```java
// 1. 함수 내부에 권한 설정하기
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
	        .antMatchers("/user/**").authenticated()
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // ADMIN만 들어갈 수 있음
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
    }
// 2. 컨트롤러에 권한 설정하기
// (1) SecurityConfig.java에 설정 : 특정 주소 접근시 권한 및 인증을 위한 어노테이션 활성화 (Controller 접근 전에 낚아서 처리)
    @EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
// (2) IndexController.java에 어노테이션 거는 방법 3가지
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostAuthorize("hasRole('ROLE_ADMIN')")
    @Secured("ROLE_ADMIN")
```

# 스프링 시큐리티 OAuth2.0 V2

- 페이스북, 구글 로그인 및 기본 시큐리티 연동

### 스프링 시큐리티 기본 V1 참고

- https://github.com/hyosun7037/Springboot-Security-OAuth2.0-V2-1

### JPA method names 참고

![blog](https://postfiles.pstatic.net/MjAyMDA4MDRfMTU1/MDAxNTk2NTA2ODAyMTgx.Qoff6FQ1RJyGw83meuDXT5J5e-Ac1WwSJMH2wf1l1Swg.KinVePXqdUOeyDYYRp4aguwTsxF0OBQB64LNUYTJRRgg.PNG.getinthere/Screenshot_26.png?type=w773)

### application.yml 설정

```yml
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: UTF-8
      enabled: true
      force: true

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/security?serverTimezone=Asia/Seoul
    username: cos
    password: cos1234

  mvc:
    view:
      prefix: /templates/
      suffix: .mustache

  jpa:
    hibernate:
      ddl-auto: update #create update none
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    show-sql: true

  security:
    oauth2:
      client:
        registration:
          google: # /oauth2/authorization/google 이 주소를 동작하게 한다.
            client-id: 머시기머시기
            client-secret: 머시기머시기
            scope:
              - email
              - profile
```
