package com.kg.konggang_guide.other.view;

import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.other.bean.PriceBean;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/9
 */

public interface IPriceView extends IView {
    String getOrderId();
    String getUserId();
    void setPriceBean(PriceBean priceBean);
}
