-- 用户表
CREATE TABLE users
(
   id              BIGINT         NOT NULL,
   username        VARCHAR(30),
   pwd             VARCHAR(255),
   identity_card   VARCHAR(18),
   create_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   update_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
