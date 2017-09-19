package com.kg.konggang_guide.other.view;

import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.other.bean.MessageBean;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/8
 */

public interface IMessageView extends IView {
    String getUserId();
    String getPageNumber();
    void setIsSuccess(boolean isSuccess);
    void setMessageBean(MessageBean messageBean);
}
