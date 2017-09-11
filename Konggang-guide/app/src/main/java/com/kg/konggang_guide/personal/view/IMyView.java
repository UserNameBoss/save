package com.kg.konggang_guide.personal.view;

import com.kg.konggang_guide.other.base.IView;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/10
 */

public interface IMyView extends IView {
    String getPhone();
    String getOldPassword();
    String getNewPassword();
    void isSuccess();
}
