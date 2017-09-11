package com.kg.konggang_guide.other.view;

import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.other.bean.LoginBean;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/4
 */

public interface ILongView extends IView {
    String getPhone();
    String getPassword();
    void setLoginBean(LoginBean loginBean);
}
