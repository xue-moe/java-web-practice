package com.yuki.servlet;

import com.yuki.dao.*;
import com.yuki.staff.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用户列表控制器
 * @author Ryosetsu_Tsan
 *
 */

@WebServlet("/staffInfoList")
@SuppressWarnings("serial")
public class StaffInfoListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询出所有员工信息
//        Staff_Jdbc sj=new Staff_Jdbc();
//        List<Staff> list=sj.select();
//        req.setAttribute("staffInfoList",list);
        req.getRequestDispatcher("/staff_info_list.jsp").forward(req,resp);

    }
}