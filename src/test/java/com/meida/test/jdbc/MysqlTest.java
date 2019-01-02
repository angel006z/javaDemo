package com.meida.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import org.junit.Assert;
import org.junit.Test;

/**
 * 什么是JDBC
 * Java 数据库连接，（Java Database Connectivity，简称JDBC）是Java语言中用来规范客户端程序如何来访问数据库的应用程序接口，提供了诸如查询和更新数据库中数据的方法。JDBC也是Sun Microsystems的商标。它JDBC是面向关系型数据库的。
 * 简单地说，就是用于执行SQL语句的一类Java API，通过JDBC使得我们可以直接使用Java编程来对关系数据库进行操作。通过封装，可以使开发人员使用纯Java API完成SQL的执行。
 */
public class MysqlTest {
	
	@Test
	public void testInsert() {
		Connection conn=getConn();
		Statement stmt = null; //一个封装和管理SQL语句的java对象
		try {			
			stmt = conn.createStatement();	         
			String sql =String.format("insert into users(username,password) values('%s','%s')", "test","123456") ;
           int result =  stmt.executeUpdate(sql);
           System.out.println(result);        
           Assert.assertEquals(result>0,true);
		} catch (SQLException e) {
			e.printStackTrace();
		}        
		
	}
	
	@Test
	public void testInsertBatch() {
		Connection conn=getConn();
		Statement stmt = null; //一个封装和管理SQL语句的java对象
		PreparedStatement ps = null;
		
		try {
			conn.setAutoCommit(false);//将提交方式设置为手动提交
			String sql = "insert into users(name,username) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			long start = System.currentTimeMillis();//获取当前系统时间的毫秒数
			for (int i = 0; i < 3000;i++) {
				ps.setInt(1, (i+1));
				ps.setString(2, "用户"+i);
				ps.setString(3, "密码"+i);
				ps.addBatch();//将给定的 SQL命令添加到此 Statement对象的当前命令列表中。
			}
			
			System.out.println("数据循环耗时："+(System.currentTimeMillis()-start)+"ms");
			
			start = System.currentTimeMillis();//获取当前系统时间的毫秒数
			//将一批命令提交给数据库来执行，如果全部命令执行成功，则返回更新计数组成的数组
			ps.executeBatch();
			conn.commit();//提交
			
			System.out.println("数据执行SQL耗时："+(System.currentTimeMillis()-start)+"ms");
		} catch (SQLException e) {
			e.printStackTrace();
		}        
		
	}
	
	/**
	   *  获取Connection
	 * @return
	 */
	private static Connection getConn() {
        //String driver = "com.mysql.jdbc.Driver";
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false";//协议:子协议://ip:端口/数据库
        String username = "root";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName(driver); //加载数据库驱动到JVM中，并实例化为Driver对象
            conn = DriverManager.getConnection(url, username, password);//建立TCP数据库长连接，获取Connection对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
