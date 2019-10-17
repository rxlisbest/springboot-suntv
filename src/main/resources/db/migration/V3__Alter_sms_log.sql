use `suntv`;

ALTER TABLE `sms_log` CHANGE COLUMN `content` `template_param` varchar(255) NOT NULL DEFAULT '' COMMENT 'TemplateParam';