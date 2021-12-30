# Base Spring Boot

###### Reference Guide

----

## Struct

크게 두가지 구성으로 나눌수 있음 
( Business - Main , Setting - Global )


##### Main

- 도메인 중심으로 구성이 됨 (Ex. Account, Noti)
- 기본적으로 Controller, Service, Repository를 메인으로 하여 구성

```
├── account
│   ├── annotation
│   │   └── validator
│   │       ├── SpaceAccountCheck.java
│   │       └── constraints
│   │           └── SpaceAccountValidator.java
│   ├── controller
│   │   └── v1
│   │       └── AccountRestController.java
│   ├── dto
│   │   ├── request
│   │   │   ├── AccountUpdateRequest.java
│   │   │   ├── AgreementRequest.java
│   │   │   ├── KakaoSignUpRequest.java
│   │   │   ├── LoginRequest.java
│   │   │   ├── PasswordModifyRequest.java
│   │   │   ├── ProfileRequest.java
│   │   │   └── SignUpRequest.java
│   │   └── response
│   │       ├── AccountInfoResponse.java
│   │       └── MyPageResponse.java
│   ├── entity
│   │   ├── Account.java
│   │   ├── embedded
│   │   └── enums
│   │       └── AccountType.java
│   ├── repository
│   │   ├── AccountRepository.java
│   │   └── extension
│   │       ├── AccountRepositoryExtension.java
│   │       └── impl
│   │           └── AccountRepositoryExtensionImpl.java
│   └── service
│       └── AccountService.java
├── main
│   └── MainRestController.java
└── notification
    ├── controller
    │   └── v1
    │       └── NotificationRestController.java
    ├── dto
    │   ├── request
    │   │   └── NotificationRequest.java
    │   └── response
    │       └── NotificationResponse.java
    ├── entity
    │   ├── Notification.java
    │   ├── embedded
    │   └── enums
    │       └── NotificationType.java
    ├── repository
    │   ├── NotificationRepository.java
    │   └── extension
    │       ├── NotificationRepositoryExtension.java
    │       └── impl
    │           └── NotificationRepositoryExtensionImpl.java
    └── service
        └── NotificationService.java
```

##### Global

- Setting 위주로 구성 (Ex. Config, Exception)
- 일부 패키지의 경우 세팅이 아닌 값이 들어간 경우도 잇음 (Ex. data, Event)

```
├── annotation
│   ├── CurrentAccount.java
│   └── constraints
│       └── BaseAccountValidator.java
├── api
│   ├── RestApiController.java
│   ├── RestApiControllerAdvice.java
│   └── RestApiResponse.java
├── config
│   ├── AppConfig.java
│   ├── AsyncConfig.java
│   ├── DatabaseConfig.java
│   ├── SwaggerConfig.java
│   ├── ValidConfig.java
│   ├── WebConfig.java
│   ├── cloud
│   │   └── amazon
│   │       └── AwsS3Config.java
│   ├── props
│   │   ├── FileProps.java
│   │   ├── GlobalProps.java
│   │   ├── JwtProps.java
│   │   ├── KakaoProps.java
│   │   ├── MailProps.java
│   │   └── aws
│   │       ├── AwsS3KeyProps.java
│   │       └── AwsS3PathProps.java
│   └── security
│       ├── SecurityConfig.java
│       ├── filter
│       │   └── TokenAuthFilter.java
│       ├── provider
│       │   └── TokenAuthProvider.java
│       └── setting
│           ├── NoRedirectStrategy.java
│           ├── SecurityDeniedJsonUtil.java
│           └── handler
│               ├── AccessDeniedCustomHandler.java
│               └── AuthenticationFailedCustomHandler.java
├── data
│   ├── dto
│   │   └── PlpResponse.java
│   └── security
│       └── UserAccount.java
├── event
│   └── log
│       ├── LogEventListener.java
│       └── dto
│           ├── ExceptionEvent.java
│           └── LogEvent.java
├── exception
│   ├── advice
│   │   └── dev
│   │       ├── ExpectRestExceptionAdvice.java
│   │       └── UnexpectRestExceptionAdvice.java
│   └── dto
│       ├── CommonException.java
│       ├── enums
│       │   └── ErrorCode.java
│       └── rest
│           └── RestReturnException.java
├── interceptor
│   └── NotificationInterceptor.java
└── utils
    ├── cloud
    │   └── awsS3
    │       └── AwsS3Util.java
    ├── error
    │   └── ErrorUtil.java
    ├── iamport
    │   ├── IamportUtil.java
    │   └── dto
    │       └── IamportInfo.java
    ├── jwt
    │   └── JwtUtil.java
    ├── kakao
    │   ├── KakaoOAuth.java
    │   └── dto
    │       ├── KakaoAccessTokenDto.java
    │       ├── KakaoAccountInfoDto.java
    │       ├── KakaoAuthDto.java
    │       └── deserializer
    │           └── KakaoAccountInfoDeserializer.java
    └── mail
        ├── ConsoleEmailService.java
        ├── EmailMessage.java
        ├── EmailService.java
        └── HtmlEmailService.java
```
