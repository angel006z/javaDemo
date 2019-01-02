package com.meida.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MssqlTest {
	public static void main(String[] args) throws SQLException {
		Connection conn=getConn();
		if(conn==null) {
			System.out.println("Connection no");
		}else {
			System.out.println("Connection yes");
		}
		
		Statement stmt = conn.createStatement();// 创建SQL命令对象
        ResultSet rs = stmt.executeQuery("SELECT * FROM depts");// 返回SQL语句查询结果集(集合)
        while (rs.next()) {
        	System.out.println(rs.getString("DeptID") + "\t" + rs.getString("DeptName"));
       }
	}

	private static Connection getConn() {

		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=DemoWebAppDB";// 协议:子协议://ip:端口/数据库
		String username = "sa";
		String password = "111111";
		Connection conn = null;
		try {
			Class.forName(driver); // 加载数据库驱动到JVM中，并实例化为Driver对象
			conn = DriverManager.getConnection(url, username, password);// 建立TCP数据库长连接，获取Connection对象
			System.out.println("Connection Successful!");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
