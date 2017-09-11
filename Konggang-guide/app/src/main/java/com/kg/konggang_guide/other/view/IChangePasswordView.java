package com.kg.konggang_guide.other.view;

import com.kg.konggang_guide.other.base.IView;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/9
 */

public interface IChangePasswordView extends IView{
    String getPhone();
    String getNewPassword();
    String getVcode();
    void isChangeSuccess();
}
