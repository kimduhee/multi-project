DROP TABLE IF EXISTS USER_INFO;
DROP TABLE IF EXISTS TEMPLATE_CATEGORY;

--관리자 정보
CREATE TABLE ADMIN_INFO (
    ADMIN_ID VARCHAR(20) NOT NULL PRIMARY KEY, --관리자 아이디
    ADMIN_NAME VARCHAR(30),  --관리자 이름
    ADMIN_PASSWORD VARCHAR(20) NOT NULL,    --관리자 비밀번호
    ADMIN_LEVEL VARCHAR(10),   --관리자 권한레벨
    REG_ID VARCHAR(20),
    REG_DT TIMESTAMP,
    UPD_ID VARCHAR(20),
    UPD_DT TIMESTAMP
);

--사용자 정보
CREATE TABLE USER_INFO (
    USER_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, --사용자 아이디
    USER_EMAIL VARCHAR(50) NOT NULL,    --사용자 이메일
    USER_NAME VARCHAR(30),  --사용자 이름
    USER_NICKNAME VARCHAR(30),  --사용자 별칭
    USER_PASSWORD VARCHAR(200) NOT NULL,    --사용자 비밀번호
    USER_ROLE VARCHAR(20) NOT NULL, --사용자 권한
    REG_ID INT,
    REG_DT TIMESTAMP,
    UPD_ID INT,
    UPD_DT TIMESTAMP
);

--템플릿 카테고리 테이블
CREATE TABLE TEMPLATE_CATEGORY (
    TEMP_CATE_ID INT PRIMARY KEY,   --카테고리 ID
    TEMP_CATE_NAME VARCHAR(100),    --카테고리 명
    TEMP_CATE_USE_YN CHAR(1),   --카테고리 사용여부(Y/N)
    TEMP_CATE_ORDER INT,    --정렬순서
    REG_ID INT,
    REG_DT TIMESTAMP,
    UPD_ID INT,
    UPD_DT TIMESTAMP
);