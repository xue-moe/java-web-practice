package com.yuki.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuki.dao.Staff_Jdbc;
import com.yuki.staff.*;
/**
 * 修改员工控制器
 * @author Ryosetsu_Tsan
 *
 */
@SuppressWarnings("serial")
@WebServlet("/staffInfoUpdate")
public class StaffInfoUpdateServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//查询需要编辑的信息
		int id=Integer.parseInt(req.getParameter("id"));
		Staff staff=new Staff_Jdbc().find(id);
		req.setAttribute("STA", staff);
		
		req.getRequestDispatcher("/staff_info_update.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收参数
		Staff staff=new Staff();
		staff.setId(Integer.parseInt(req.getParameter("id")));
		staff.setName(new String(req.getParameter("name").getBytes("ISO-8859-1"),"UTF-8"));
		staff.setGender(new String(req.getParameter("gender").getBytes("ISO-8859-1"),"UTF-8"));
		staff.setBirthday(req.getParameter("birthday"));
		staff.setPhone(req.getParameter("phone"));
		boolean ret=new Staff_Jdbc().gai(staff);
		req.getRequestDispatcher("staffInfoList").forward(req,resp);
	}
}
