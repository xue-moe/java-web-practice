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

/** 首页汇总员工数量；统计逻辑由 DAO 执行，页面只负责展示。 */
@WebServlet("/")
public class StaffHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StaffDao staffDao = new StaffDao();
        try {
            request.setAttribute("totalCount", staffDao.countAll());
            request.setAttribute("maleCount", staffDao.countByGender("男"));
            request.setAttribute("femaleCount", staffDao.countByGender("女"));
            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
        } catch (SQLException e) {
            getServletContext().log("Unable to load dashboard", e);
            WebSupport.showError(request, response, 500,
                    "暂时无法读取员工数据。请确认数据库已启动并检查 DB_URL、DB_USER 和 DB_PASSWORD 配置。 ");
        }
    }
}
