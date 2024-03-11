package com.example.petpetpet.ui.adopt.PopupWindow;

public class TimeBean implements BaseFilter{
    /**
     * 时间str
     */
    String timeStr;
    /**
     * 时间事件
     */
    String timeEvent;

    public TimeBean(String timeStr, String timeEvent) {
        this.timeStr = timeStr;
        this.timeEvent = timeEvent;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getTimeEvent() {
        return timeEvent;
    }

    public void setTimeEvent(String timeEvent) {
        this.timeEvent = timeEvent;
    }

    @Override
    public String getFilterStr() {
        return timeStr;
    }
}