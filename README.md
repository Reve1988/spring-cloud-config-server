### Spring Cloud Config Server

![Spring Cloud Config Server 구조](https://i0.wp.com/blog.leekyoungil.com/wp-content/uploads/2017/04/1.png?resize=1024%2C762)

설정방법은 application.yml 참고

Git을 이용한 설정

PlaceHolder
Environment로 사용할 수 있는 변수는 3가지
1. {application} : 어플리케이션명, 클라이언트의 spring.application.name에 해당
2. {profile} : 어플리케이션이 동작할 프로파일명, 클라이언트의 spring.profile.active에 해당, 콤마(,)를 이용하여 여러개의 프로파일을 적용할 수 있다.
3. {label} : 설정 서버에서 사용하는 값으로 주로 설정에 대한 버전 정보로 사용 

예시

Config Repository : https://github.com/Reve1988/spring-cloud-config-test
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

spring.cloud.config.server.git
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