package com.example.hr.servlet;

import com.example.hr.dao.StaffDao;
import com.example.hr.staff.Staff;
import com.example.hr.util.WebSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/** 读取员工列表，并把搜索条件和结果交给 JSP 渲染。 */
@WebServlet("/staffInfoList")
public class StaffInfoListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // GET 参数用于搜索，因此刷新或分享查询链接时筛选条件仍然保留。
        String keyword = request.getParameter("q");
        String gender = request.getParameter("gender");
        request.setAttribute("keyword", keyword == null ? "" : keyword.trim());
        request.setAttribute("genderFilter", gender == null ? "" : gender);
        request.setAttribute("flashMessage", WebSupport.takeFlash(request));

        try {
            List<Staff> staffList = new StaffDao().findAll(keyword, gender);
            request.setAttribute("staffList", staffList);
            request.setAttribute("staffCount", staffList.size());
            request.getRequestDispatcher("/WEB-INF/views/staff_list.jsp").forward(request, response);
        } catch (SQLException e) {
            getServletContext().log("Unable to load staff list", e);
            WebSupport.showError(request, response, 500,
                    "暂时无法读取员工列表。请确认数据库已启动并检查连接配置。");
        }
    }
}
