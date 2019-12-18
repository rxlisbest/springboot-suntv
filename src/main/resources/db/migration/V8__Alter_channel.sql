use `suntv`;

ALTER TABLE `channel` ADD COLUMN `family_id` INT(11) NOT NULL DEFAULT 0 COMMENT '家庭ID';