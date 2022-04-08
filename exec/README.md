## ⚙️ 환경
JVM : Open jdk 11 <br>
Spring : 2.6.4 <br>
Node.js : 16.13.2 <br>
React : 17.0 <br>
ubuntu : 20.04 <br>

### 디렉토리 구조
#### backend
```
.
└── grpc-common
    ├── src
    │   └── main
    │       └── proto
    │           └── common.proto
    │           └── notificationMailService.proto
    │           └── notificationSmsService.proto
    └── build.gradle
.    
└── module-api
    ├── main
    │   └── java
    │       └── polling
    │           └── auth
    │               ├── adapter
    │               ├── controller
    │               ├── dto
    │               ├── oauth
    │               ├── service
    │               └── CurrentUser
    │           └── common
    │               ├── entity
    │               └── web
    │           └── contact
    │               ├── controller
    │               ├── dto
    │               ├── entity
    │               ├── repository
    │               └── service
    │           └── grpc
    │               └── client
    │                   ├── dto
    │                   └── stub
    │           └── member
    │               ├── controller
    │               ├── dto
    │               ├── entity
    │               ├── repository
    │               └── service
    │           └── poll
    │               ├── poll
    │               ├── candidate
    │               └── comment
    │           └── token
    │               ├── controller
    │               ├── dto
    │               ├── entity
    │               ├── repository
    │               └── service
    └── resources
```

#### frontend
```
Polling
└── src
    ├── App.js
    ├── Modal
    │	
    ├── Components 
    │   ├── Common
    │   │   ├── App
    │   ├── Member
    ├── Routes
    │   ├── poll
    │   ├── Member
    │   │   ├── SignUp
    │   │   └── SignIn
    │   ├── Contact
    │   ├── Candidate
    │   ├── Setting
    │   └── Review
    ├── api
    ├── assets
    ├── data
    └── store
        └── reducers
```
### 도커
저희는 jenkins, spring 모듈들, redis, maria db를 각각의 컨테이너로 배포했습니다. <br>
react : 젠킨스 빌드 후 nginx 설정으로 연결 <br>
spring : spring-compose, spring-external-compose <br>
jenkins : jenkins-compose <br>
rdbms : mariadb <br>
nosql : redis <br>

## 빌드 및 배포 방법
### nginx 설정 파일
![nginx](/uploads/7e5e8a0b67d3285318f8c03053c7170d/nginx.PNG)

### 프론트 <br>

#### 리액트 설정파일
![react](/uploads/1cfbe20149cbd231de5c60a38e1613b5/react.PNG)

front 브랜치에 리액트 프로젝트를 푸쉬하면 깃랩 웹훅으로 아래 명령으로 빌드 실행 <br>

```
cd frontend
npm install && CI='' npm run build
```

이후 nginx로 ec2에 연결된 url을 리액트 빌드 결과 index.html로 연결

### 백엔드

#### 스프링 설정파일
![spring](/uploads/bc9269d71904b67496fabea6086f7670/spring.PNG)

backend 브랜치에 리액트 프로젝트를 푸쉬하면 깃랩 웹훅으로 아래 명령으로 빌드 실행 <br>

```
cd ./module/polling && chmod +x gradlew
./gradlew clean build
```
이후 젠킨스 컨테이너안에 있는 팸키 정보로 ec2에 접속해 아래 명령으로 배포 실행 <br>

```
ssh -t -t -i /key.pem ubuntu@172.26.8.164 <<EOF
cd ~/compose/spring/
docker-compose up --build -d && docker restart spring-compose
exit
EOF
```
