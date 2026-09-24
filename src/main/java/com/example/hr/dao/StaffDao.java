package com.example.hr.dao;

import com.example.hr.staff.Staff;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** 员工表的数据访问层。SQL 参数统一使用 PreparedStatement。 */
public class StaffDao {
    public List<Staff> findAll(String keyword, String gender) throws SQLException {
        // 可选筛选条件只拼接 SQL 结构，用户输入仍通过 ? 参数绑定。
        StringBuilder sql = new StringBuilder(
                "SELECT staff_id, staff_name, staff_sex, staff_birthday, staff_phone " +
                "FROM tb_staff_info WHERE 1 = 1");
        List<String> values = new ArrayList<String>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append(" AND (staff_name LIKE ? OR staff_phone LIKE ?)");
            String pattern = "%" + keyword.trim() + "%";
            values.add(pattern);
            values.add(pattern);
        }
        if ("男".equals(gender) || "女".equals(gender)) {
            sql.append(" AND staff_sex = ?");
            values.add(gender);
        }
        sql.append(" ORDER BY staff_id DESC");

        List<Staff> staffList = new ArrayList<Staff>();
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            // PreparedStatement 的参数下标从 1 开始，顺序与上面追加的条件一致。
            for (int i = 0; i < values.size(); i++) {
                statement.setString(i + 1, values.get(i));
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    staffList.add(readStaff(resultSet));
                }
            }
        }
        return staffList;
    }

    public Staff findById(int id) throws SQLException {
        String sql = "SELECT staff_id, staff_name, staff_sex, staff_birthday, staff_phone " +
                     "FROM tb_staff_info WHERE staff_id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? readStaff(resultSet) : null;
            }
        }
    }

    public int countAll() throws SQLException {
        return count("SELECT COUNT(*) FROM tb_staff_info", null);
    }

    public int countByGender(String gender) throws SQLException {
        return count("SELECT COUNT(*) FROM tb_staff_info WHERE staff_sex = ?", gender);
    }

    public boolean create(Staff staff) throws SQLException {
        // try-with-resources 会在成功或抛出异常时关闭连接和语句。
        String sql = "INSERT INTO tb_staff_info (staff_name, staff_sex, staff_birthday, staff_phone) " +
                     "VALUES (?, ?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            fillStatement(statement, staff);
            return statement.executeUpdate() == 1;
        }
    }

    public boolean update(Staff staff) throws SQLException {
        String sql = "UPDATE tb_staff_info SET staff_name = ?, staff_sex = ?, " +
                     "staff_birthday = ?, staff_phone = ? WHERE staff_id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            fillStatement(statement, staff);
            statement.setInt(5, staff.getId());
            return statement.executeUpdate() == 1;
        }
    }

    public boolean delete(int id) throws SQLException {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM tb_staff_info WHERE staff_id = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate() == 1;
        }
    }

    private int count(String sql, String value) throws SQLException {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (value != null) {
                statement.setString(1, value);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        }
    }

    private void fillStatement(PreparedStatement statement, Staff staff) throws SQLException {
        statement.setString(1, staff.getName());
        statement.setString(2, staff.getGender());
        statement.setDate(3, Date.valueOf(staff.getBirthday()));
        statement.setString(4, staff.getPhone());
    }

    private Staff readStaff(ResultSet resultSet) throws SQLException {
        Staff staff = new Staff();
        staff.setId(resultSet.getInt("staff_id"));
        staff.setName(resultSet.getString("staff_name"));
        staff.setGender(resultSet.getString("staff_sex"));
        staff.setBirthday(resultSet.getDate("staff_birthday").toString());
        staff.setPhone(resultSet.getString("staff_phone"));
        return staff;
    }
}
