package com.example.petpetpet.ui.adopt.PopupWindow;

public class PlaceBean implements BaseFilter{
    String placeStr;

    public PlaceBean(String placeStr) {
        this.placeStr = placeStr;
    }

    public String getPlaceStr() {
        return placeStr;
    }

    public void setPlaceStr(String placeStr) {
        this.placeStr = placeStr;
    }

    @Override
    public String getFilterStr() {
        return placeStr;
    }
}
