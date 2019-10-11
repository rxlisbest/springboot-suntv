use `suntv`;

-- 用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS  `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '名称',
  `username` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '密码',
  `cellphone` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `create_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='用户表';

-- 文件表
DROP TABLE IF EXISTS `file`;
CREATE TABLE IF NOT EXISTS  `file` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `domain` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '域名',
  `key` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '文件名',
  `content_type` VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'Content-Type',
  `number` INT(11) NOT NULL DEFAULT 0 COMMENT '文件数量',
  `transcoding_code` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '转码code',
  `source_key` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '源文件文件名',
  `source_content_type` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '源文件Content-Type',
  `create_user_id` INT(11) NOT NULL DEFAULT 0 COMMENT '创建用户ID',
  `create_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='文件表';

-- 频道分类表
DROP TABLE IF EXISTS `channel_category`;
CREATE TABLE IF NOT EXISTS  `channel_category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '名称',
  `create_user_id` INT(11) NOT NULL DEFAULT 0 COMMENT '创建用户ID',
  `create_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='频道分类表';

-- 频道表
DROP TABLE IF EXISTS `channel`;
CREATE TABLE IF NOT EXISTS  `channel` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `channel_category_id` INT(11) NOT NULL DEFAULT 0 COMMENT '频道分类ID',
  `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '名称',
  `file_id` INT(11) NOT NULL DEFAULT 0 COMMENT '文件ID',
  `url` VARCHAR(1000) NOT NULL DEFAULT '' COMMENT '链接',
  `create_user_id` INT(11) NOT NULL DEFAULT 0 COMMENT '创建用户ID',
  `create_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='频道表';

-- 用户频道关系表
DROP TABLE IF EXISTS `user_channel`;
CREATE TABLE IF NOT EXISTS  `user_channel` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `channel_id` INT(11) NOT NULL DEFAULT 0 COMMENT '频道ID',
  `create_user_id` INT(11) NOT NULL DEFAULT 0 COMMENT '创建用户ID',
  `create_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `channel_id` (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='用户频道关系表';

-- 家庭表
DROP TABLE IF EXISTS `family`;
CREATE TABLE IF NOT EXISTS  `family` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '名称',
  `create_user_id` INT(11) NOT NULL DEFAULT 0 COMMENT '创建用户ID',
  `create_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='家庭表';

-- 用户家庭关系表
DROP TABLE IF EXISTS `user_family`;
CREATE TABLE IF NOT EXISTS  `user_family` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `family_id` INT(11) NOT NULL DEFAULT 0 COMMENT '家庭ID',
  `create_user_id` INT(11) NOT NULL DEFAULT 0 COMMENT '创建用户ID',
  `create_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `family_id` (`family_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='用户家庭关系表';

-- 短信日志表
DROP TABLE IF EXISTS `sms_log`;
CREATE TABLE `sms_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cellphone` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `template_code` varchar(255) NOT NULL DEFAULT '' COMMENT 'TemplateCode',
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT 'CODE',
  `create_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `cellphone` (`cellphone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='短信日志表';