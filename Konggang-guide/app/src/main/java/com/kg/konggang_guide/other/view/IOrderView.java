package com.kg.konggang_guide.other.view;

import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.other.bean.CityFromBean;
import com.kg.konggang_guide.other.bean.SearchAddressBean;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/7
 */

public interface IOrderView extends IView {
    String getGuideId();
    String getMileage();
    String getTime();
    String getIsOut();
    String getOutMile();
    String getCityCode();
    void setAddress(SearchAddressBean searchAddressBean);
    void setOrderId(String orderId,int type);
    void setPrice(String price);
    void setCityFrom(CityFromBean cityFrom);


    String getOrderId();
    String getUserId();
    String getDepartureLocation();
    String getArrivedLocation();
    String getName();
    String getTelephone();
    String getIsSend();
    String getCarType();
    String getEstimatePrice();

    String getCity();
    String getAirport();
    String getTerminalBuilding();
    String getAirId();
    String getDeparturePosition();
    String getArrivedPosition();
    String getArriredTime();
    String getOTime();
    String getArriedTime();
    void isOrderSuccess();
}
