package com.example.hr.servlet;

import com.example.hr.dao.StaffDao;
import com.example.hr.staff.Staff;
import com.example.hr.util.StaffForm;
import com.example.hr.util.WebSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/** GET 展示新增表单；POST 校验并保存表单数据。 */
@WebServlet("/staffInfoSave")
public class StaffInfoSaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("staff", new Staff());
        request.setAttribute("mode", "create");
        request.getRequestDispatcher("/WEB-INF/views/staff_form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 先把请求参数转换成对象，再集中校验，避免把规则散落在页面里。
        Staff staff = StaffForm.read(request, 0);
        String error = StaffForm.validate(staff);
        if (error != null) {
            showForm(request, response, staff, error);
            return;
        }

        try {
            if (new StaffDao().create(staff)) {
                WebSupport.flash(request, "员工信息已添加。");
                response.sendRedirect(request.getContextPath() + "/staffInfoList");
            } else {
                showForm(request, response, staff, "没有保存员工信息，请稍后重试。");
            }
        } catch (SQLException e) {
            getServletContext().log("Unable to create staff record", e);
            showForm(request, response, staff, "保存失败。请确认数据库已启动并检查连接配置。");
        }
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response,
                          Staff staff, String error) throws ServletException, IOException {
        request.setAttribute("staff", staff);
        request.setAttribute("mode", "create");
        request.setAttribute("errorMessage", error);
        request.getRequestDispatcher("/WEB-INF/views/staff_form.jsp").forward(request, response);
    }
}
