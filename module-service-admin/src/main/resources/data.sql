INSERT INTO ADMIN_INFO (
    ADMIN_ID, ADMIN_NAME, ADMIN_PASSWORD, ADMIN_LEVEL,
    REG_ID, REG_DT, UPD_ID, UPD_DT
) VALUES (
    'admin', '관리자', '1234', '1',
    'admin', NOW(), 'admin', NOW()
);

INSERT INTO ADMIN_INFO (
    ADMIN_ID, ADMIN_NAME, ADMIN_PASSWORD, ADMIN_LEVEL,
    REG_ID, REG_DT, UPD_ID, UPD_DT
) VALUES (
    'admin1', '관리자1', '1234', '1',
    'admin', NOW(), 'admin', NOW()
);

INSERT INTO ADMIN_INFO (
    ADMIN_ID, ADMIN_NAME, ADMIN_PASSWORD, ADMIN_LEVEL,
    REG_ID, REG_DT, UPD_ID, UPD_DT
) VALUES (
    'admin2', '관리자2', '1234', '1',
    'admin', NOW(), 'admin', NOW()
);

INSERT INTO USER_INFO (
    USER_EMAIL, USER_NAME, USER_NICKNAME, USER_PASSWORD, USER_ROLE,
    REG_ID, REG_DT, UPD_ID, UPD_DT
) VALUES (
    'oo2706@naver.conm', '최길동', '배부른작가', '1234', 'ROLE_USER',
    9999, NOW(), 9999, NOW()
);
