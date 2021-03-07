package com.yuki.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.yuki.servlet.index;
import com.yuki.staff.*;

public class Staff_Jdbc {
    private Connection conn = null;
    public PreparedStatement pst = null;
    public ResultSet rst = null;
    private String sql=null;
    private boolean ret=false;

    //增删改
    public boolean zeng(Staff staff){
        sql="insert into tb_staff_info values(null,?,?,?,?)";
        ret = Transform(sql, staff.getName(), staff.getGender(), staff.getBirthday(), staff.getPhone());
        return ret;
    }
    public boolean shan(Staff staff){
        sql="delete from tb_staff_info where staff_id=?";
       ret = Transform(sql, staff.getId());
        return ret;
    }
    public boolean gai(Staff staff){
        sql="update tb_staff_info set staff_name=?,staff_sex=?,staff_birthday=?,staff_phone=? where staff_id=?)";
        ret = Transform(sql, staff.getName(), staff.getGender(), staff.getBirthday(), staff.getPhone(),staff.getId());
        return ret;
    }
    //变
    private boolean Transform(String sql, Object... objects) {
        try {
            conn = DbConnection.getConn();
            pst = conn.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                pst.setObject(i + 1, objects[i]);
            }
            int rows = pst.executeUpdate();
            DbConnection.getClose(conn, pst, rst);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //查
    public List<Staff> select() {
        List<Staff> StaList=new ArrayList<>();
        conn = DbConnection.getConn();
        try {
            sql="select * from tb_staff_info;";
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            while(rst.next()) {
                Staff staff=new Staff();
                staff.setId(rst.getInt("staff_id"));
                staff.setName(rst.getString("staff_name"));
                staff.setGender(rst.getString("staff_sex"));
                staff.setBirthday(rst.getString("staff_birthday"));
                staff.setPhone(rst.getString("staff_phone"));
                StaList.add(staff);
            }
            DbConnection.getClose(conn, pst, rst);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return StaList;
    }
	public Staff find(int id) {
        conn = DbConnection.getConn();
        Staff staff=new Staff();
        try {
            sql="select * from tb_staff_info where staff_id="+id;
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            while(rst.next()) {
                staff.setId(id);
                staff.setName(rst.getString("staff_name"));
                staff.setGender(rst.getString("staff_sex"));
                staff.setBirthday(rst.getString("staff_birthday"));
                staff.setPhone(rst.getString("staff_phone"));
            }
            DbConnection.getClose(conn, pst, rst);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;
    }
    
    
	
//	  public static void main(String[] args) { 
//		 Staff sta=new Staff_Jdbc().find(1); 
//		System.out.println(sta);
//		  
//	  }
	
}
