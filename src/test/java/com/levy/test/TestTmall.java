package com.levy.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class TestTmall {
	/**
	 * ������������
	 * @param args
	 */
	public static void main(String[] args) {
		Connection connection = null;
		Statement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/tmall_springboot?"
					+ "useUnicode=true&characterEncoding=utf8","root","root");
			
			ps = connection.createStatement();
			for(int i = 1; i <= 10;i++) {
				String sqlFormat = "insert into category values(null,'���Է���%d')";
				String sql = String.format(sqlFormat, i);
				ps.execute(sql);
			}
			System.out.println("�ɹ�����10������");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
