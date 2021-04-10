# User table
DROP TABLE IF EXISTS user;
CREATE TABLE user (
                                `phone` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID，手机号码',
                                `nickname` varchar(255) NOT NULL,
                                `password` varchar(32) DEFAULT NULL,
                                `avatar` varchar(128) DEFAULT NULL COMMENT '头像，云存储的ID',
                                `register_date` datetime DEFAULT NULL COMMENT '注册时间',
                                PRIMARY KEY (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO user VALUES ('15000000000', 'user0', 'user0p', null, '2019-01-09 17:08:16');