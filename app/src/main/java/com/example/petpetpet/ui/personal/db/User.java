package com.example.petpetpet.ui.personal.db;

public class User {

    private static int userId;
    private static String userPassword;
    private static String userName;
    private static int userType;//社区0或个人1
    private static String userCommunity;//所在社区


    public User() {
    }

    public User(int userId, String userPassword, String userName, int userType, String userCommunity) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userType = userType;//社区0或个人1
        this.userCommunity = userCommunity;
    }

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        User.userId = userId;
    }

    public static String getUserPassword() {
        return userPassword;
    }

    public static void setUserPassword(String userPassword) {
        User.userPassword = userPassword;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        User.userName = userName;
    }

    public static int getUserType() {
        return userType;
    }

    public static void setUserType(int userType) {
        User.userType = userType;
    }

    public static String getUserCommunity() {
        return userCommunity;
    }

    public static void setUserCommunity(String userCommunity) {
        User.userCommunity = userCommunity;
    }
}



