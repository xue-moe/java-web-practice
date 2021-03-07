package com.yuki.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yuki.dao.Staff_Jdbc;
import com.yuki.staff.Staff;

/**
 * 新增员工控制器
 * @author Ryosetsu_Tsan
 *
 */
@SuppressWarnings("serial")
@WebServlet("/staffInfoSave")
public class StaffInfoSaveServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/staff_info_save.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收参数
		Staff staff=new Staff();
		staff.setName(new String(req.getParameter("name").getBytes("ISO-8859-1"),"UTF-8"));
		staff.setGender(new String(req.getParameter("gender").getBytes("ISO-8859-1"),"UTF-8"));
		staff.setBirthday(req.getParameter("birthday"));
		staff.setPhone(req.getParameter("phone"));
		boolean ret=new Staff_Jdbc().zeng(staff);
		req.getRequestDispatcher("staffInfoList").forward(req,resp);
		System.out.println(ret);
		System.out.println(staff.toString());
	}
	
}
