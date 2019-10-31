use `suntv`;

ALTER TABLE `channel_category` ADD COLUMN `family_id` INT(11) NOT NULL DEFAULT 0 COMMENT '家庭ID';
DROP TABLE `user_channel`;