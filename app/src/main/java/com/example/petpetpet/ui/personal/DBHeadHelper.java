package com.example.petpetpet.ui.personal;

import com.example.petpetpet.mysql.DBOpenHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class DBHeadHelper {

    /**
     * 插入数据（插入一条姓名为 name，年龄为 age 的数据）
     */

    public static void insert(int userId, String head) {
        // 插入数据的 sql 语句
        String sql = "insert into test (userId, head) values (null, ?)";
        Connection connection = DBOpenHelper.getConn();
        PreparedStatement ps = null;
        if (connection == null) {
            return;
        }
        try {
            ps = connection.prepareStatement(sql);
            // 为? 设置具体的值，userId自增
            ps.setString(1, head);
            // 执行语句
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    /**
     * 查询 person 表的所有数据
     */
    public static String query(int userId) {
        // 查询的 sql 语句
        String sql = "select * from test where userId = " + userId;
        Connection connection = DBOpenHelper.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String findName = "啥也没找到！！";
        StringBuilder builder = new StringBuilder();
        try {
            ps = connection.prepareStatement(sql);
            // 执行语句（执行查询语句用的是 executeQuery 方法）
            rs = ps.executeQuery();
            // 得到查询结果
            if (rs != null) {
                /**
                 * 输出所有uesr表内容
                  */
//                while (rs.next()) {
//                    builder.append("[userId = ");
//                    builder.append(rs.getString("userId"));
//                    builder.append(", userName = ");
//                    builder.append(rs.getString("userName"));
//                    builder.append("] ");
//                }

//                while (!Objects.equals(rs.getString("userName"), userInterName))
//                    rs.next();

                while (rs.next()) {
                    if (Objects.equals(rs.getInt(userId), userId))
                        return rs.getString("head");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

//        return builder.toString();
        return findName;
    }


}
