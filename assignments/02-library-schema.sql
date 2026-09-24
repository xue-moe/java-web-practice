-- 练习 02：图书借阅管理的 MySQL 表结构。
-- book 和 reader 保存基础资料；每次借阅单独写入 loan 历史表。
-- returned_at 为 NULL 表示尚未归还，借出与归还时应同步维护库存。
CREATE DATABASE IF NOT EXISTS library_practice
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE library_practice;

CREATE TABLE book (
  id INT NOT NULL AUTO_INCREMENT COMMENT '图书主键',
  title VARCHAR(120) NOT NULL COMMENT '书名',
  author VARCHAR(80) NOT NULL COMMENT '作者',
  isbn VARCHAR(20) NOT NULL COMMENT '唯一 ISBN',
  total_copies INT NOT NULL COMMENT '馆藏总册数',
  available_copies INT NOT NULL COMMENT '当前可借册数',
  PRIMARY KEY (id),
  UNIQUE KEY uk_book_isbn (isbn),
  CHECK (total_copies >= 0 AND available_copies >= 0 AND available_copies <= total_copies)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE reader (
  id INT NOT NULL AUTO_INCREMENT COMMENT '读者主键',
  reader_no VARCHAR(30) NOT NULL COMMENT '唯一读者编号',
  name VARCHAR(60) NOT NULL COMMENT '读者姓名',
  phone VARCHAR(20) NOT NULL COMMENT '联系电话；练习中使用虚构数据',
  PRIMARY KEY (id),
  UNIQUE KEY uk_reader_no (reader_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE loan (
  id INT NOT NULL AUTO_INCREMENT COMMENT '借阅记录主键',
  book_id INT NOT NULL COMMENT '关联图书',
  reader_id INT NOT NULL COMMENT '关联读者',
  borrowed_at DATE NOT NULL COMMENT '借出日期',
  due_at DATE NOT NULL COMMENT '应还日期',
  returned_at DATE NULL COMMENT '实际归还日期；NULL 表示未归还',
  PRIMARY KEY (id),
  INDEX idx_loan_book (book_id),
  INDEX idx_loan_reader (reader_id),
  INDEX idx_loan_returned (returned_at),
  CONSTRAINT fk_loan_book FOREIGN KEY (book_id) REFERENCES book (id),
  CONSTRAINT fk_loan_reader FOREIGN KEY (reader_id) REFERENCES reader (id),
  CHECK (due_at >= borrowed_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
