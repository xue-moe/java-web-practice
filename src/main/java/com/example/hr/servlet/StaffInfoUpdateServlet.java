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

/** 按编号读取并更新员工；两个请求都先检查编号和记录是否有效。 */
@WebServlet("/staffInfoUpdate")
public class StaffInfoUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // URL 参数始终是不可信输入；在访问数据库前先转换并验证正整数编号。
        int id = WebSupport.positiveId(request.getParameter("id"));
        if (id == 0) {
            WebSupport.showError(request, response, 400, "员工编号无效。");
            return;
        }
        try {
            Staff staff = new StaffDao().findById(id);
            if (staff == null) {
                WebSupport.showError(request, response, 404, "没有找到这条员工记录。");
                return;
            }
            request.setAttribute("staff", staff);
            request.setAttribute("mode", "update");
            request.getRequestDispatcher("/WEB-INF/views/staff_form.jsp").forward(request, response);
        } catch (SQLException e) {
            getServletContext().log("Unable to load staff record", e);
            WebSupport.showError(request, response, 500,
                    "暂时无法读取员工信息。请检查数据库连接配置。");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = WebSupport.positiveId(request.getParameter("id"));
        if (id == 0) {
            WebSupport.showError(request, response, 400, "员工编号无效。");
            return;
        }
        // 表单校验失败时保留用户已经填写的值，方便修正后重新提交。
        Staff staff = StaffForm.read(request, id);
        String error = StaffForm.validate(staff);
        if (error != null) {
            showForm(request, response, staff, error);
            return;
        }

        try {
            if (new StaffDao().update(staff)) {
                WebSupport.flash(request, "员工信息已更新。");
                response.sendRedirect(request.getContextPath() + "/staffInfoList");
            } else {
                WebSupport.showError(request, response, 404, "员工记录不存在或已被删除。");
            }
        } catch (SQLException e) {
            getServletContext().log("Unable to update staff record", e);
            showForm(request, response, staff, "更新失败。请确认数据库已启动并检查连接配置。");
        }
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response,
                          Staff staff, String error) throws ServletException, IOException {
        request.setAttribute("staff", staff);
        request.setAttribute("mode", "update");
        request.setAttribute("errorMessage", error);
        request.getRequestDispatcher("/WEB-INF/views/staff_form.jsp").forward(request, response);
    }
}
