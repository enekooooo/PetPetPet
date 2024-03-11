package com.example.petpetpet.ui.adopt.AdoptCardPost;

import android.graphics.Bitmap;

public class AdoptCardItem {

    private int PetId;//动物ID
    private int UserId;//拥有者ID
    private int PetSex;//动物性别
    private int PetState;//领养状态
    private int Heart;//1喜欢or0未喜欢
    private Bitmap PetImageId;//动物图片
    private String PetName;//动物名称
    private String PetCommunity;//动物所属社区
    private String PetLocate;//动物位置（如江苏南京栖霞区）

    public AdoptCardItem(int petId, int userId, int petSex, int petState, int heart, Bitmap petImageId, String petName, String petCommunity, String petLocate) {
        PetId = petId;
        UserId = userId;
        PetSex = petSex;
        PetState = petState;
        Heart = heart;
        PetImageId = petImageId;
        PetName = petName;
        PetCommunity = petCommunity;
        PetLocate = petLocate;
    }

    public AdoptCardItem() {

    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getPetId() {
        return PetId;
    }

    public void setPetId(int petId) {
        PetId = petId;
    }

    public Bitmap getPetImageId() {
        return PetImageId;
    }

    public void setPetImageId(Bitmap petImageId) {
        PetImageId = petImageId;
    }

    public int getPetSex() {
        return PetSex;
    }

    public void setPetSex(int petSex) {
        PetSex = petSex;
    }

    public int getPetState() {
        return PetState;
    }

    public void setPetState(int petState) {
        PetState = petState;
    }

    public int getHeart() {
        return Heart;
    }

    public void setHeart(int heart) {
        Heart = heart;
    }

    public String getPetName() {
        return PetName;
    }

    public void setPetName(String petName) {
        PetName = petName;
    }

    public String getPetCommunity() {
        return PetCommunity;
    }

    public void setPetCommunity(String petCommunity) {
        PetCommunity = petCommunity;
    }

    public String getPetLocate() {
        return PetLocate;
    }

    public void setPetLocate(String petLocate) {
        PetLocate = petLocate;
    }
}
