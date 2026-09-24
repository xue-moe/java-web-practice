-- 员工档案项目使用的 MySQL 初始化脚本。
-- 示例记录均为虚构内容；本脚本可重复执行，不会重复插入相同手机号。
CREATE DATABASE IF NOT EXISTS hrmanagement
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE hrmanagement;

CREATE TABLE IF NOT EXISTS tb_staff_info (
  staff_id INT NOT NULL AUTO_INCREMENT COMMENT '员工主键编号',
  staff_name VARCHAR(20) NOT NULL COMMENT '员工姓名',
  staff_sex CHAR(4) NOT NULL COMMENT '性别，只接受男或女',
  staff_birthday DATE NOT NULL COMMENT '出生日期',
  staff_phone VARCHAR(11) NOT NULL COMMENT '手机号；用于练习搜索',
  PRIMARY KEY (staff_id),
  INDEX idx_staff_name (staff_name),
  INDEX idx_staff_phone (staff_phone)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO tb_staff_info (staff_name, staff_sex, staff_birthday, staff_phone)
SELECT '张明', '男', '1998-06-01', '13800000001'
WHERE NOT EXISTS (SELECT 1 FROM tb_staff_info WHERE staff_phone = '13800000001');
INSERT INTO tb_staff_info (staff_name, staff_sex, staff_birthday, staff_phone)
SELECT '李华', '女', '1999-03-01', '13800000002'
WHERE NOT EXISTS (SELECT 1 FROM tb_staff_info WHERE staff_phone = '13800000002');
INSERT INTO tb_staff_info (staff_name, staff_sex, staff_birthday, staff_phone)
SELECT '王小雨', '女', '2000-08-15', '13800000003'
WHERE NOT EXISTS (SELECT 1 FROM tb_staff_info WHERE staff_phone = '13800000003');
