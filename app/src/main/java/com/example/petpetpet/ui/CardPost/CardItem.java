package com.example.petpetpet.ui.CardPost;

public class CardItem {
    private int PostImageId;
    private int HeadImageId;
    private String Title;
    private String UserName;
    private int Heart;

    public CardItem() {

    }

    public CardItem(int postImageId, int headImageId, String title, String userName, int heart) {
        PostImageId = postImageId;
        HeadImageId = headImageId;
        Title = title;
        UserName = userName;
        Heart = heart;
    }

    public int getPostImageId() {
        return PostImageId;
    }

    public void setPostImageId(int postImageId) {
        PostImageId = postImageId;
    }

    public int getHeadImageId() {
        return HeadImageId;
    }

    public void setHeadImageId(int headImageId) {
        HeadImageId = headImageId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getHeart() {
        return Heart;
    }

    public void setHeart(int heart) {
        Heart = heart;
    }



}
