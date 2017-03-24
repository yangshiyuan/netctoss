package com.netctoss.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

    private static String user;
    private static String password;
    private static String url;
    private static String driver;
    private static ThreadLocal<Connection> tl =
            new ThreadLocal<Connection>();

    static {
        Properties p = new Properties();
        try {
            p.load(DBUtil.class.getClassLoader()
                    .getResourceAsStream("db.properties"));
            user = p.getProperty("user");
            password = p.getProperty("password");
            url = p.getProperty("url");
            driver = p.getProperty("driver");
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(
                    "读取数据库配置文件失败！");
        }
    }

    /**
     * 获取连接
     */
    public static Connection getConnection() {
        Connection con = tl.get();
        if (con == null) {
            try {
                con = DriverManager.getConnection(
                        url, user, password);
                tl.set(con);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(
                        "获取连接失败！");
            }
        }
        return con;
    }

    /**
     * 关闭连接
     */
    public static void closeConnection() {
        Connection con = tl.get();
        if (con != null) {
            try {
                con.close();
                tl.set(null);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(
                        "关闭连接失败！");
            }
        }
    }

    public static void main(String[] args)
            throws Exception {
        System.out.println(DBUtil.getConnection());
        DBUtil.closeConnection();
    }

}
