package com.jxgy.util;

import java.sql.*;

public class DbUtil {

	public static Connection getConnection() {
		Connection conn= null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/lkz?setUnicode=true&characterEncoding=UTF-8","root","root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	public static void closeAll
		(Connection conn,Statement stmt,ResultSet rs){
		try {
			if(rs!=null) {
				rs.close();
			}
			if(stmt!=null) {
				stmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	}
		
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}
