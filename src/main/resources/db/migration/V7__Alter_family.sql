use `suntv`;

ALTER TABLE `family` ADD COLUMN `is_official` TINYINT NOT NULL DEFAULT 0 COMMENT '是否官方：1-是 0-否';

INSERT INTO `family` (`name`, `create_user_id`, `create_time`, `update_time`, `status`, `is_official`) VALUES ('SunTV官方', 0, unix_timestamp(now()), unix_timestamp(now()), 1, 1);

ALTER TABLE `user_family` ADD COLUMN `is_default` TINYINT NOT NULL DEFAULT 0 COMMENT '是否默认：1-是 0-否';