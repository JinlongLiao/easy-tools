create table TB_ADMIN_ROLE
(
    ID          varchar(64)                         not null,
    ADD_BY      varchar(255)                        null,
    ADD_TIME    timestamp default CURRENT_TIMESTAMP not null,
    UPDATE_BY   varchar(255)                        null,
    UPDATE_TIME timestamp default CURRENT_TIMESTAMP not null,
    ROLE_NAME   varchar(32)                         not null,
    ROLE_VALUE  varchar(32)                         not null
)
    collate = utf8mb4_bin;

alter table TB_ADMIN_ROLE
    add primary key (ID);

INSERT INTO TB_ADMIN_ROLE (ID, ADD_BY, ADD_TIME, UPDATE_BY, UPDATE_TIME, ROLE_NAME, ROLE_VALUE)
VALUES ('18c91b0de3274ec490440c82bfe5d35a', 'f318b973c5f3401b887730c1223bd9f1', '2019-12-02 13:41:36',
        'f318b973c5f3401b887730c1223bd9f1', '2019-12-02 13:41:36', '审计管理员', 'auditAdmin');
INSERT INTO TB_ADMIN_ROLE (ID, ADD_BY, ADD_TIME, UPDATE_BY, UPDATE_TIME, ROLE_NAME, ROLE_VALUE)
VALUES ('2493bf2e5f0445a092f5b686b482578d', 'f318b973c5f3401b887730c1223bd9f1', '2019-09-03 13:16:03', null,
        '2019-12-02 13:40:54', '超级管理员', 'superAdmin');
INSERT INTO TB_ADMIN_ROLE (ID, ADD_BY, ADD_TIME, UPDATE_BY, UPDATE_TIME, ROLE_NAME, ROLE_VALUE)
VALUES ('8561fc31eec5459e890893d1589d5d8b', 'f318b973c5f3401b887730c1223bd9f1', '2019-09-03 13:16:03', null,
        '2019-12-02 13:41:03', '系统管理员', 'sysAdmin');
INSERT INTO TB_ADMIN_ROLE (ID, ADD_BY, ADD_TIME, UPDATE_BY, UPDATE_TIME, ROLE_NAME, ROLE_VALUE)
VALUES ('916c4dbd636c4f6da45d926f14338b43', 'f318b973c5f3401b887730c1223bd9f1', '2019-12-02 13:41:18',
        'f318b973c5f3401b887730c1223bd9f1', '2019-12-02 13:41:18', '安全管理员', 'secAdmin');

create table user
(
    id       bigint auto_increment
        primary key,
    password varchar(255) null,
    role     varchar(255) null,
    username varchar(255) null
);

INSERT INTO user (id, password, role, username)
VALUES (1, '123456', 'ROLE_USER', 'vector');

