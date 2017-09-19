package com.kg.konggang_guide.other.presenter;

import android.app.Activity;

import com.kg.konggang_guide.AppConstants;
import com.kg.konggang_guide.other.base.CpBasePresenter;
import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.bean.MessageBean;
import com.kg.konggang_guide.other.module.MessageModule;
import com.kg.konggang_guide.other.view.IMessageView;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/8
 */

public class MessagePresenter extends CpBasePresenter {

    private MessageModule messageModule;
    private IMessageView iMessageView;

    public MessagePresenter(IView iView) {
        super(iView);
        messageModule=new MessageModule((Activity) iView);
        iMessageView= (IMessageView) iView;
    }

    public void getMyMessage(){
        messageModule.requestServerDataOne(new OnBaseDataListener<MessageBean>() {
            @Override
            public void onNewData(MessageBean data) {
                if(data.code== AppConstants.SUCCESS_CODE){
                    iMessageView.setMessageBean(data);
                    iMessageView.setIsSuccess(true);
                }else{
                    iMessageView.showToask(data.msg);
                    iMessageView.setIsSuccess(false);
                }
            }

            @Override
            public void onError(String code) {
                iMessageView.showToask(code);
                iMessageView.setIsSuccess(false);
            }
        },iMessageView.getUserId(),iMessageView.getPageNumber());
    }
}
