package com.kg.konggang_guide.other.event;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/21
 */

public class MyEvent {
    private int type;
    private boolean isRefresh;

    public int getType() {
        return type;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }
}
