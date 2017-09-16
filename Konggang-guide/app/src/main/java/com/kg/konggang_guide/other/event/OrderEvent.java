package com.kg.konggang_guide.other.event;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/9/8
 */

public class OrderEvent {
    private boolean isRefresh;
    private int type;
    private String message;
    private String currTime;

    public String getCurrTime() {
        return currTime;
    }

    public void setCurrTime(String currTime) {
        this.currTime = currTime;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsRefresh() {
        return isRefresh;
    }

    public void setIsRefresh(boolean isRefresh) {
        this.isRefresh = isRefresh;
    }
}
