
# 개발환경
+ JDK 17(17.0.13)
+ IDE IntelliJ
  <br/><br/>
- - -
# 기본 모듈구성
### multi-module
전체 모듈들의 부모 모듈
+ settings.gradle(모듈 구성원 정의)
<pre><code>rootProject.name = 'multi-module'
include 'module-common'
include 'module-mybatis-h2'
include 'module-jpa-h2'
include 'module-security'
include 'module-redis'
include 'module-service-web'
include 'module-service-app'
include 'module-service-admin'
</code></pre>
+ build.gradle(하위 모듈들에 포함될 lib 정의)
<pre><code>plugins {
	id 'java'
	id 'org.springframework.boot' version '3.1.4'
	id 'io.spring.dependency-management' version '1.1.3'
	id 'java-library'
}

bootJar.enabled = false
jar.enabled = true

subprojects {

	group 'com.framework'
	version '0.0.1-SNAPSHOT'

	sourceCompatibility = '17'

	apply plugin: 'java'
	apply plugin: 'java-library'
	apply plugin: 'org.springframework.boot'
	apply plugin: 'io.spring.dependency-management'

	repositories {
		mavenCentral()
	}

	dependencies {
		//validation lib
		implementation 'org.springframework.boot:spring-boot-starter-validation'
		//웹요청 처리 lib
		implementation 'org.springframework.boot:spring-boot-starter-web'
		//입.출력 lib
		implementation 'commons-io:commons-io:2.7'

		//lombok
		compileOnly 'org.projectlombok:lombok'
		annotationProcessor 'org.projectlombok:lombok'
		//@ConfigurationProperties를 사용하기 위한 lib
		annotationProcessor 'org.springframework.boot:spring-boot-configuration-processor'
		testImplementation 'org.springframework.boot:spring-boot-starter-test'
	}

	configurations {
		compileOnly {
			extendsFrom annotationProcessor
		}
	}

}

clean {
	delete file('src/main/generated')
}
</code></pre>
### module-common
프레임워크 관련 공통 설정 모듈
### module-jpa-h2
H2 DB의 jpa를 사용하기 위한 설정 모듈
### module-mybatis-h2
H2 DB의 MyBatis를 사용하기 위한 설정 모듈
### module-security
Spring Security를 사용하기 위한 설정 모듈
### module-redis
redis를 사용하기 위한 설정 및 UTIL 모듈
### module-service-XXXXX
공통적 모듈들에서 필요한 모듈들을 조합하여 실제 서비스 될 모듈 
- - -
# 기본 설정
### 환경변수 설정
+ 기본적으로 [local], [dev], [stg], [prd] 로 구성되어 있으며 다음과 같은 환경변수를 적용해준다
<pre><code>-Dspring.profiles.active=[환경변수값]</code></pre>
- - -