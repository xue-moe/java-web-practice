package com.yuki.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuki.dao.Staff_Jdbc;
import com.yuki.staff.Staff;

/**
 * 删除员工控制器
 * @author Ryosetsu_Tsan
 *
 */
@WebServlet("/staffInfoDelete")
@SuppressWarnings("serial")
public class StaffInfoDeleteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//根据id删除
		Staff staff=new Staff();
		staff.setId(Integer.parseInt(req.getParameter("id")));
		boolean ret=new Staff_Jdbc().shan(staff);
		req.getRequestDispatcher("staffInfoList").forward(req,resp);
	}

}
