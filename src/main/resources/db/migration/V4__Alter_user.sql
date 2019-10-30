use `suntv`;

ALTER TABLE `user` ADD COLUMN `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1-启用 0-禁用';