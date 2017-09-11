package com.kg.konggang_guide.personal.view;

import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.personal.bean.MyOrderBean;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/7
 */

public interface IMyOrderView extends IView {

    String getGuideId();
    void setOrderList(MyOrderBean myOrderBean);
}
