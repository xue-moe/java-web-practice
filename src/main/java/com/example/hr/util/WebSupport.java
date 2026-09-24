package com.example.hr.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class WebSupport {
    private WebSupport() { }

    public static int positiveId(String value) {
        // 无效编号统一返回 0，让调用处可以用一个简单条件处理。
        try {
            int id = Integer.parseInt(value);
            return id > 0 ? id : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void showError(HttpServletRequest request, HttpServletResponse response,
                                 int status, String message)
            throws ServletException, IOException {
        response.setStatus(status);
        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
    }

    public static void flash(HttpServletRequest request, String message) {
        // Session 暂存一次性提示，重定向后的列表页读取后会马上清除。
        request.getSession().setAttribute("flashMessage", message);
    }

    public static String takeFlash(HttpServletRequest request) {
        Object value = request.getSession().getAttribute("flashMessage");
        request.getSession().removeAttribute("flashMessage");
        return value == null ? null : value.toString();
    }
}
