DROP TABLE IF EXISTS SAMPLE;

--샘플 테이블
CREATE TABLE SAMPLE
(
    SAMPLE_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,   --ID
    SAMPLE_GROUP VARCHAR(10),    --그룹
    SAMPLE_NAME VARCHAR(10),     --명
    REG_ID VARCHAR(100),
    REG_DT DATE,
    UPD_ID VARCHAR(100),
    UPD_DT DATE
);