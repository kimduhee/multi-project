INSERT INTO ADMIN_INFO (
    ADMIN_ID, ADMIN_NAME, ADMIN_PASSWORD, ADMIN_LEVEL, USE_YN,
    REG_NO, REG_DT, UPD_NO, UPD_DT
) VALUES (
    'admin', '통합관리자', '1234', '1', 'Y',
    1, NOW(), 1, NOW()
);

INSERT INTO ADMIN_INFO (
    ADMIN_ID, ADMIN_NAME, ADMIN_PASSWORD, ADMIN_LEVEL, USE_YN,
    REG_NO, REG_DT, UPD_NO, UPD_DT
) VALUES (
    'admin1', '관리자1', '1234', '1', 'Y',
    2, NOW(), 2, NOW()
);

INSERT INTO USER_INFO (
    USER_EMAIL, USER_NAME, USER_NICKNAME, USER_PASSWORD, USER_ROLE,
    REG_NO, REG_DT, UPD_NO, UPD_DT
) VALUES (
    'oo2706@naver.conm', '최길동', '배부른작가', '1234', 'ROLE_USER',
    9999, NOW(), 9999, NOW()
);

INSERT INTO TEMPLATE_CATEGORY (
    TEMP_CATE_NO, TEMP_PARENTS_CATE_NO, TEMP_CATE_NAME, USE_YN, TEMP_CATE_ORDER,
    REG_NO, REG_DT, UPD_NO, UPD_DT
) VALUES (
    1, 0, '일상', 'Y', 1,
    9999, NOW(), 9999, NOW()
);

INSERT INTO TEMPLATE_CATEGORY (
    TEMP_CATE_NO, TEMP_PARENTS_CATE_NO, TEMP_CATE_NAME, USE_YN, TEMP_CATE_ORDER,
    REG_NO, REG_DT, UPD_NO, UPD_DT
) VALUES (
    2, 0, '여행', 'Y', 2,
    9999, NOW(), 9999, NOW()
);

INSERT INTO TEMPLATE_CATEGORY (
    TEMP_CATE_NO, TEMP_PARENTS_CATE_NO, TEMP_CATE_NAME, USE_YN, TEMP_CATE_ORDER,
    REG_NO, REG_DT, UPD_NO, UPD_DT
) VALUES (
    3, 0, '웹툰', 'Y', 3,
    9999, NOW(), 9999, NOW()
);

INSERT INTO TEMPLATE_CATEGORY (
    TEMP_CATE_NO, TEMP_PARENTS_CATE_NO, TEMP_CATE_NAME, USE_YN, TEMP_CATE_ORDER,
    REG_NO, REG_DT, UPD_NO, UPD_DT
) VALUES (
    4, 0, '소설', 'Y', 4,
    9999, NOW(), 9999, NOW()
);

INSERT INTO TEMPLATE_CATEGORY (
    TEMP_CATE_NO, TEMP_PARENTS_CATE_NO, TEMP_CATE_NAME, USE_YN, TEMP_CATE_ORDER,
    REG_NO, REG_DT, UPD_NO, UPD_DT
) VALUES (
    5, 4, '무협', 'Y', 1,
    9999, NOW(), 9999, NOW()
);

INSERT INTO TEMPLATE_CATEGORY (
    TEMP_CATE_NO, TEMP_PARENTS_CATE_NO, TEMP_CATE_NAME, USE_YN, TEMP_CATE_ORDER,
    REG_NO, REG_DT, UPD_NO, UPD_DT
) VALUES (
    6, 4, '코믹', 'Y', 2,
    9999, NOW(), 9999, NOW()
);
