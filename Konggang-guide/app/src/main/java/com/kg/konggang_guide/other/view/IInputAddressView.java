package com.kg.konggang_guide.other.view;

import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.other.bean.AddressBean;
import com.kg.konggang_guide.other.bean.CityBean;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/8
 */

public interface IInputAddressView extends IView{

    String getCityId();
    String getSearchName();
    void setCityBean(CityBean cityBean);
    void setSearchAddress(AddressBean addressBean);
}
