CREATE TABLE `t_user`
(
    `id`   int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;