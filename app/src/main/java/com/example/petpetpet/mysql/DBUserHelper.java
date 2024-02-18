package com.example.petpetpet.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class DBUserHelper {

    /**
     * 插入数据（插入一条姓名为 name，年龄为 age 的数据）
     */

    public static void insert(String userName, String userPassword, int userType, String userCommunity) {
        // 插入数据的 sql 语句
        String sql = "insert into user (userId, userName, userPassword, userType, userCommunity) values (null, ?, ?, ?, ?)";
        Connection connection = DBOpenHelper.getConn();
        PreparedStatement ps = null;
        if (connection == null) {
            return;
        }
        try {
            ps = connection.prepareStatement(sql);
            // 为? 设置具体的值，userId自增
            ps.setString(1, userName);
            ps.setString(2,userPassword);
            ps.setInt(3,userType);
            ps.setString(4,userCommunity);
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
     * 更新数据（将姓名为 name 的年龄改为 newAge）
     */
    public static void update(String name, int newAge) {
        // 更新数据的 sql 语句
        String sql = "update person set age = ? where id = ?";
        Connection connection = DBOpenHelper.getConn();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 为两个 ? 设置具体的值
            ps.setInt(1, newAge);
            ps.setString(2, name);
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
     * 删除数据（删除姓名为 name 的数据）
     */
    public static void delete(String name) {
        // 删除数据的 sql 语句
        String sql = "delete from person where name = ?";
        Connection connection = DBOpenHelper.getConn();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 为 ? 设置具体的值
            ps.setString(1, name);
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
    public static String query(String queryType, String returnType, String userInterName) {
        // 查询的 sql 语句
        String sql = "select * from user";
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
                    if (Objects.equals(rs.getString(queryType), userInterName))
                        return rs.getString(returnType);
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
