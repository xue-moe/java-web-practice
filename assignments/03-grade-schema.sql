-- 练习 03：课程成绩管理的 MySQL 表结构。
-- 学生与课程是多对多关系，score 用外键连接两张基础表。
-- 联合唯一约束保证一名学生在一门课程中只有一条当前成绩。
CREATE DATABASE IF NOT EXISTS grades_practice
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE grades_practice;

CREATE TABLE student (
  id INT NOT NULL AUTO_INCREMENT COMMENT '学生主键',
  student_no VARCHAR(30) NOT NULL COMMENT '唯一学号',
  name VARCHAR(60) NOT NULL COMMENT '学生姓名',
  class_name VARCHAR(80) NOT NULL COMMENT '行政班级',
  PRIMARY KEY (id),
  UNIQUE KEY uk_student_no (student_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE course (
  id INT NOT NULL AUTO_INCREMENT COMMENT '课程主键',
  course_no VARCHAR(30) NOT NULL COMMENT '唯一课程编号',
  title VARCHAR(120) NOT NULL COMMENT '课程名称',
  credits DECIMAL(3,1) NOT NULL COMMENT '课程学分',
  PRIMARY KEY (id),
  UNIQUE KEY uk_course_no (course_no),
  CHECK (credits > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE score (
  id INT NOT NULL AUTO_INCREMENT COMMENT '成绩主键',
  student_id INT NOT NULL COMMENT '关联学生',
  course_id INT NOT NULL COMMENT '关联课程',
  score DECIMAL(5,2) NOT NULL COMMENT '0 到 100 分',
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY uk_score_student_course (student_id, course_id),
  INDEX idx_score_course (course_id),
  CONSTRAINT fk_score_student FOREIGN KEY (student_id) REFERENCES student (id),
  CONSTRAINT fk_score_course FOREIGN KEY (course_id) REFERENCES course (id),
  CHECK (score >= 0 AND score <= 100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
