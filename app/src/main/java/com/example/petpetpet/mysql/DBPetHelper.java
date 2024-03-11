package com.example.petpetpet.mysql;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Toast;

import com.example.petpetpet.ui.StringAndBitmap;
import com.example.petpetpet.ui.adopt.AdoptCardPost.AdoptCardItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DBPetHelper {

    /**
     * 插入数据
     */
    private static final String TAG = "DBPetHelper";
    public static void insert(String petImageId, int petSex, int petState, String petName,
                              String petCommunity, String petType, String petLocate, int petUserId) {
        // 插入数据的 sql 语句
        String sql =
                "insert into pet (petId, petImageId, petSex, petState, petName, petCommunity, petType, petLocate, petUserId)" +
                        " values (null, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DBOpenHelper.getConn();
        PreparedStatement ps = null;
        if (connection == null) {
            return;
        }
        try {
            ps = connection.prepareStatement(sql);
            // 为? 设置具体的值，userId自增
            ps.setString(1, petImageId);
            ps.setInt(2,petSex);
            ps.setInt(3,petState);
            ps.setString(4,petName);
            ps.setString(5,petCommunity);
            ps.setString(6,petType);
            ps.setString(7,petLocate);
            ps.setInt(8,petUserId);
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
    public static void update(int PetId, String petImageId, int petSex, int petState, String petName,
                              String petCommunity, String petType, String petLocate) {
        // 更新数据的 sql 语句
        String sql = "update pet set petImageId = ? , petSex = ? , petState = ? , petName = ? ," +
                " petCommunity = ? , petType = ? , petLocate = ? where PetId = ?";
        Connection connection = DBOpenHelper.getConn();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 为两个 ? 设置具体的值

            ps.setString(1, petImageId);
            ps.setInt(2, petSex);
            ps.setInt(3, petState);
            ps.setString(4, petName);
            ps.setString(5, petCommunity);
            ps.setString(6, petType);
            ps.setString(7, petLocate);
            ps.setInt(8, PetId);
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
        String sql = "select * from pet";
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
                while (rs.next()) {
                    builder.append("[userId = ");
                    builder.append(rs.getString("userId"));
                    builder.append(", userName = ");
                    builder.append(rs.getString("userName"));
                    builder.append("] ");
                }

                while (!Objects.equals(rs.getString("userName"), userInterName))
                    rs.next();

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

    /**
     * 查询 pet 表的所有数据
     */
    public static List<AdoptCardItem> queryAll() {

        List<AdoptCardItem> cardItemArrayList=new ArrayList<AdoptCardItem>();
        String sql = "select * from pet";
        Connection connection = DBOpenHelper.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String findName = "啥也没找到！！";
        StringAndBitmap stringAndBitmap = new StringAndBitmap();

        try {
            ps = connection.prepareStatement(sql);
            // 执行语句（执行查询语句用的是 executeQuery 方法）
            rs = ps.executeQuery();
            // 得到查询结果5/12
            if (rs != null) {
                /**
                 * 遍历所有pet表内容
                 */
                while (rs.next()) {
                    AdoptCardItem adoptCardItem = new AdoptCardItem();

                    adoptCardItem.setPetId(rs.getInt("petId"));


                    adoptCardItem.setPetImageId(stringAndBitmap.stringToBitmap(rs.getString("petImageId")));//获取String数据转换为bitmap数据
                    adoptCardItem.setPetSex(rs.getInt("petSex"));
                    adoptCardItem.setPetState(rs.getInt("petState"));
                    adoptCardItem.setHeart(0);
                    adoptCardItem.setPetName(rs.getString("petName"));
                    adoptCardItem.setPetCommunity(rs.getString("petCommunity"));
                    adoptCardItem.setPetLocate(rs.getString("petLocate"));
                    adoptCardItem.setUserId(rs.getInt("petUserId"));

                    cardItemArrayList.add(adoptCardItem);

                }

            }else
                Log.d(TAG, "queryAll: 查找失败！");

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
        return cardItemArrayList;
    }

}
