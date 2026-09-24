package com.example.hr.servlet;

import com.example.hr.dao.StaffDao;
import com.example.hr.util.WebSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/** 删除使用 POST，避免用户预览链接或爬虫访问时意外修改数据。 */
@WebServlet("/staffInfoDelete")
public class StaffInfoDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = WebSupport.positiveId(request.getParameter("id"));
        if (id == 0) {
            WebSupport.flash(request, "删除失败：员工编号无效。");
            response.sendRedirect(request.getContextPath() + "/staffInfoList");
            return;
        }
        try {
            boolean deleted = new StaffDao().delete(id);
            WebSupport.flash(request, deleted ? "员工信息已删除。" : "记录不存在或已被删除。");
        } catch (SQLException e) {
            getServletContext().log("Unable to delete staff record", e);
            WebSupport.flash(request, "删除失败。请检查数据库连接配置。");
        }
        response.sendRedirect(request.getContextPath() + "/staffInfoList");
    }
}
