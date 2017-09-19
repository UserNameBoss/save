package com.kg.konggang_guide.other.view;

import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.other.bean.DriverListBean;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/7
 */

public interface IMainView extends IView {
    String getTerminalBuilding();

    //改派的订单Id
    String getOrderId();
    String getPhone();
    String getRemark();
    void isChangeGuide();

    //接单的Id
    String getOrderId02();
    String getGuideId();
    void getOrderSuccess(boolean isSuccess);

    String getType();
    String getDirId();
    String getCityId();
    void isSendCarSuccess(boolean isSuccess);
    void setMessageNoRead(int count);


    void setDriverList(DriverListBean driverList);
}
