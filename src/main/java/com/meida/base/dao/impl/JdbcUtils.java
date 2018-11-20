package com.meida.base.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {
	private static Properties prop = new Properties();    
    static{        
        try {
            //加载jdbc.properties配置文件
            prop.load(JdbcUtils.class.getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //设置常量
    public static final String DRIVER = prop.getProperty("jdbc.driver");
    public static final String URL = prop.getProperty("jdbc.url");
    public static final String USERNAME = prop.getProperty("jdbc.username");
    public static final String PASSWORD = prop.getProperty("jdbc.password");
    
    /**
     * 注册驱动 获取连接
     * 
     * @return
     */
    public Connection getConnection() {
        try {           
            // 注册驱动
            Class.forName(DRIVER);           
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            //e.printStackTrace("数据库的驱动文件没有找到");
        } catch (SQLException e) {
            //数据库的连接错误
            e.printStackTrace();
        }
        return null;
    }
    
    public void closeAll(Connection conn,Statement st) {
		closeAll(conn, st,null);
	}
    /**
     * 关闭连接释放资源
     * @param con
     * @param st
     * @param rt
     */
    public void closeAll(Connection conn, Statement st, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (st != null) {
                st.close();
                st = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
