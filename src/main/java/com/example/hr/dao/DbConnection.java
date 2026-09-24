package com.example.hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** 读取环境变量创建数据库连接；本地练习默认值只用于个人开发环境。 */
public final class DbConnection {
    private static final String DEFAULT_URL =
            "jdbc:mysql://localhost:3306/hrmanagement?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&characterEncoding=UTF-8";

    private DbConnection() { }

    public static Connection getConnection() throws SQLException {
        // 容器或 Docker 可注入环境变量；本机练习时才使用下面的默认值。
        String url = setting("DB_URL", DEFAULT_URL);
        String user = setting("DB_USER", "root");
        String password = setting("DB_PASSWORD", "root");
        return DriverManager.getConnection(url, user, password);
    }

    private static String setting(String name, String fallback) {
        String value = System.getenv(name);
        return value == null || value.trim().isEmpty() ? fallback : value.trim();
    }
}
