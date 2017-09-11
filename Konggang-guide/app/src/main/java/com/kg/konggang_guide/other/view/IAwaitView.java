package com.kg.konggang_guide.other.view;

import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.other.bean.OrderBean;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/9
 */

public interface IAwaitView extends IView {

    String getGuideId();
    String getType();
    String getPageNum();
    void isSuccess(int code);
    void setOrderBean(OrderBean orderBean);
}
