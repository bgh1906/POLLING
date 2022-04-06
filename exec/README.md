## ⚙️ 환경
JVM : Open jdk 11 <br>
Spring : 2.5.9 <br>
Node.js : 16.13.2 <br>
React : 17.0 <br>
ubuntu : 20.04 <br>

### 디렉토리 구조
#### backend
```
.
└── main
    ├── generated
    ├── java
    │   └── com
    │       └── glanner
    │           ├── api
    │           │   ├── controller
    │           │   ├── service
    │           │   ├── queryrepository
    │           │   ├── exception
    │           │   │   └── handler
    │           │   ├── dto
    │           │   │   ├── request
    │           │   │   └── response
    │           ├── core
    │           │   ├── domain
    │           │   │   ├── user
    │           │   │   ├── glanner
    │           │   │   └── board
    │           │   └── repository
    │           ├── security
    │           │   └── jwt
    │           ├── aop
    │           │   ├── aspect
    │           │   ├── annotation
    │           │   └── logtrace
    │           └── config
    └── resources
```

#### frontend
```
Glanner
└── src
    ├── App.js
    ├── Modal
    │	
    ├── Components 
    │   ├── Common
    │   │   ├── App
    │   ├── Member
    ├── Routes
    │   ├── Community
    │   ├── Member
    │   │   ├── SignUp
    │   │   └── SignIn
    │   ├── Conference
    │   ├── Planner
    │   ├── Setting
    │   └── Review
    ├── api
    ├── assets
    ├── data
    └── store
        └── reducers
```
### 도커
저희는 젠킨스, 리액트 프로젝트, 스프링 프로젝트, DB, OpenVidu를 각각의 컨테이너로 배포했습니다. <br>
리액트 : react-compose <br>
스프링 : spring-compose <br>
젠킨스 : jenkins-compose <br>
DB : mariadb <br>
OpenVidu : clever_noether <br>

## 빌드 및 배포 방법
#### 프론트 <br>
release-front 브랜치에 리액트 프로젝트를 푸쉬하면 깃랩 웹훅으로 아래 명령으로 빌드 실행 <br>

```
cd ./frontend/glanner
node -v
npm -v
npm install
npm run build
```

이후 젠킨스 컨테이너안에 있는 팸키 정보로 ec2에 접속해 아래 명령으로 배포 실행 <br>

```
ssh -t -t -i /key.pem ubuntu@172.26.11.12 <<EOF
cd ~/compose/react/
docker-compose build --no-cache && docker-compose up -d
exit
EOF
```

#### 백엔드
release-backend 브랜치에 리액트 프로젝트를 푸쉬하면 깃랩 웹훅으로 아래 명령으로 빌드 실행 <br>

```
cd ./backend/glanner
./gradlew clean build
```
이후 젠킨스 컨테이너안에 있는 팸키 정보로 ec2에 접속해 아래 명령으로 배포 실행 <br>

```
ssh -t -t -i /key.pem ubuntu@172.26.11.12 <<EOF
cd ~/compose/spring/
docker-compose build --no-cache && docker-compose up -d
exit
EOF
```
