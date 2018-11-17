# Spring Cloud Config Server

### 1. Git을 이용한 설정

Git : https://git-scm.com/  
Document : https://cloud.spring.io/spring-cloud-config/multi/multi__spring_cloud_config_server.html#_git_backend

![Git을 이용한 Spring Cloud Config Server](https://i0.wp.com/blog.leekyoungil.com/wp-content/uploads/2017/04/1.png?resize=1024%2C762)

#### Environment
1. {application} : 어플리케이션명, 클라이언트의 spring.application.name에 해당
2. {profile} : 어플리케이션이 동작할 프로파일명, 클라이언트의 spring.profile.active에 해당, 콤마(,)를 이용하여 여러개의 프로파일을 적용할 수 있다.
3. {label} : 설정 서버에서 사용하는 값으로 주로 설정에 대한 버전 정보로 사용 

#### Config Repository

예제 : https://github.com/Reve1988/spring-cloud-config-test

호출 예 : 
```
http://localhost:8888/testApplication/local
http://localhost:8888/testApplication/dev
http://localhost:8888/testApplication/staging
http://localhost:8888/testApplication/real

http://localhost:8888/testApplication/local/v2
http://localhost:8888/testApplication/dev/v2
http://localhost:8888/testApplication/staging/v2
http://localhost:8888/testApplication/real/v2
```

#### Config Server 설정

`src/main/resources/application.yml` 파일 참고

spring.cloud.config.server.git.~
- uri : Git 저장소 주소
- uri가 SSH일 경우
  - ignoreLocalSshSettings : true로 설정하면 파일 기반이 아닌 SSH기반의 설정 정보를 사용
  - privateKey : 유효한 SSH Private Key 입력
  - hostKey : 유효한 Host Key 입력, Host Key를 사용할 경우 hostKeyAlgorithm이 필수
  - hostKeyAlgorithm : ssh-dss, ssh-rsa, ecdsa-sha2-nistp256, ecdsa-sha2-nistp384 ,ecdsa-sha2-nistp521 중 하나만 사용 가능
  - strictHostKeyChecking : Host Key 에 이상이 없는지 확인 여부 true, false
  - knownHostsFile : 직접 작성한 알려진 호스트 파일 경로 (운영체제와 별도로 사용하고 싶을 때)
  - preferredAuthentications : Override server authentication method order. This should allow evade login prompts if server has keyboard-interactive authentication before `publickey` method.
- uri가 https일 경우
  - username : 사용자 ID
  - password : 암호 (Git 저장소가 비공개가 아니라면 필수 아님)
- searchPaths : 추가로 설정파일을 탐색할 경로, 콤마(,)로 여러 경로 추가 가능  
```위 예시에서 http://localhost:8888/testApplication/message/v3```  
마지막에 '{application}'을 넣는다면 디렉토리로 어플리케이션 구분이 가능
```위 예시에서 http://localhost:8888/secondApplication/dev/v4```
- force-pull : 설정 파일을 가져올 때 마다 clone을 사용, 간혹 문제가 발생해서 pull이 되지 않을 때 이 설정을 true로 설정하면 해결 가능
- deleteUntrackedBranches : 간혹 {label}을 제거하기 위해 원격 브랜치를 지우기도 하는데, Config Server가 해당 브랜치를 이미 로컬에 가지고 있을 경우 계속해서 서비스가 된다. 이 때, 이 옵션을 true로 하면 해결 가능 

참고 : 스프링 부트 실행시 profile을 지정하려면 `-Dspring.profiles.active=local` 옵션 

### 2. vault를 이용한 설정

Vault : https://www.vaultproject.io/  
Document : https://cloud.spring.io/spring-cloud-config/multi/multi__spring_cloud_config_server.html#vault-backend
