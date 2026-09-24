package com.example.hr.util;

import com.example.hr.staff.Staff;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/** 把表单字段转换为员工对象，并执行最基本的服务端校验。 */
public final class StaffForm {
    private StaffForm() { }

    public static Staff read(HttpServletRequest request, int id) {
        Staff staff = new Staff();
        staff.setId(id);
        staff.setName(value(request, "name"));
        staff.setGender(value(request, "gender"));
        staff.setBirthday(value(request, "birthday"));
        staff.setPhone(value(request, "phone"));
        return staff;
    }

    public static String validate(Staff staff) {
        // 返回第一条可读错误信息；返回 null 表示字段通过基础校验。
        if (staff.getName().isEmpty() || staff.getName().length() > 20) {
            return "姓名必填，且不能超过 20 个字符。";
        }
        if (!"男".equals(staff.getGender()) && !"女".equals(staff.getGender())) {
            return "请选择有效的性别。";
        }
        try {
            LocalDate birthday = LocalDate.parse(staff.getBirthday());
            if (birthday.isAfter(LocalDate.now())) {
                return "生日不能晚于今天。";
            }
        } catch (DateTimeParseException | NullPointerException e) {
            return "请选择有效的出生日期。";
        }
        if (!staff.getPhone().matches("1[3-9][0-9]{9}")) {
            return "请输入 11 位有效手机号。";
        }
        return null;
    }

    private static String value(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        return value == null ? "" : value.trim();
    }
}
