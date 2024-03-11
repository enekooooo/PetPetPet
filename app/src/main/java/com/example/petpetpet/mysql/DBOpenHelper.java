package com.example.petpetpet.mysql;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBOpenHelper {
    static String ip = "10.0.2.2";
    static int port = 3306;
    static String dbName = "petdb";

    private static String diver = "com.mysql.jdbc.Driver";
    //加入utf-8是为了后面往表中输入中文，表中不会出现乱码的情况
    private static String url = "jdbc:mysql://" + ip + ":" + port
            + "/" + dbName+"?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static String user = "root";//用户名
    private static String password = "1234";//密码
    private static Connection conn = null;
    /*
     * 连接数据库
     * */
    public static Connection getConn(){
        try {
            Class.forName(diver);
            Log.d(TAG, "加载JDBC驱动成功");
            conn = (Connection) DriverManager.getConnection(url,user,password);//获取连接
            Log.d(TAG, "连接数据库成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库
     */
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




}
