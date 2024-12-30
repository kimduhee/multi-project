
# 개발환경
+ JDK 17(17.0.13)
+ IDE IntelliJ
  <br/><br/>
- - -
# 기본 모듈구성
### multi-module
+ 전체 모듈들의 부모 모듈
> + settings.gradle
>><code>rootProject.name = 'multi-module'<br>
include 'module-service-web'<br>
include 'module-service-app'<br>
include 'module-common'<br>
include 'module-security'<br>
include 'module-mybatis-h2'<br>
include 'module-jpa-h2'<br>
include 'module-redis'<br>
include 'module-service-admin'<br>
</code>
### module-common
+ 프레임워크 관련 공통 설정 모듈
### module-jpa-h2
+ H2 DB의 jpa를 사용하기 위한 설정 모듈
### module-mybatis-h2
+ H2 DB의 MyBatis를 사용하기 위한 설정 모듈
### module-security
+ Spring Security를 사용하기 위한 설정 모듈
### module-redis
+ redis를 사용하기 위한 설정 및 UTIL 모듈
### module-service-XXXXX
+ 공통적 모듈들에서 필요한 모듈들을 조합하여 실제 서비스 될 모듈 
- - -
# 기본 설정
### 환경변수 설정
+ 기본적으로 [local], [dev], [stg], [prd] 로 구성되어 있으며 다음과 같은 환경변수를 적용해준다
<pre><code>-Dspring.profiles.active=[환경변수값]</code></pre>
- - -