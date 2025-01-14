CREATE DATABASE `messaging_test`;
CREATE USER 'messaging'@'%' IDENTIFIED BY 'messaging2021';
GRANT ALL PRIVILEGES ON *.* TO messaging@'%';
USE messaging_test;

CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `username` varchar(128) CHARACTER SET utf8 DEFAULT NULL,
                        `nickname` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL,
                        `password` varchar(128) CHARACTER SET utf8 DEFAULT NULL,
                        `login_token` varchar(128) CHARACTER SET utf8 DEFAULT NULL,
                        `register_time` datetime DEFAULT NULL,
                        `last_login_time` datetime DEFAULT NULL,
                        `gender` varchar(128) DEFAULT NULL,
                        `email` varchar(128) DEFAULT NULL,
                        `address` varchar(128) DEFAULT NULL,
                        `is_valid` tinyint(1) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        KEY `username_index` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user_validation_code` (
                                        `id` int NOT NULL AUTO_INCREMENT,
                                        `user_id` int DEFAULT NULL,
                                        `validation_code` varchar(8) DEFAULT NULL,
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

