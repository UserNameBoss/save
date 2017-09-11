package com.kg.konggang_guide.other.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.adapter.MessageAdapter;
import com.kg.konggang_guide.other.base.CpBaseActivty;
import com.kg.konggang_guide.other.bean.MessageBean;
import com.kg.konggang_guide.other.event.MyEvent;
import com.kg.konggang_guide.other.presenter.MessagePresenter;
import com.kg.konggang_guide.other.utils.DialogUtils;
import com.kg.konggang_guide.other.utils.StatusBarUtil;
import com.kg.konggang_guide.other.view.IMessageView;
import com.kg.konggang_guide.personal.AppState;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class MessageActivity extends CpBaseActivty implements IMessageView {


    @BindView(R.id.xrl_message)
    XRecyclerView xrlMessage;
    private MessageAdapter messageAdapter;

    private MessagePresenter messagePresenter;
    private MessageBean messageBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);

        initViews();
        initEvents();
        initDatas();
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setOiStatusBarColor(this);
        StatusBarUtil.StatusBarLightMode(this);
        supportTitle(true);
        title.setTitleText("消息中心");
        title.setLeftImage(R.mipmap.app_back);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrlMessage.setLayoutManager(linearLayoutManager);
        messagePresenter=new MessagePresenter(this);
    }

    @Override
    protected void initEvents() {
        MyEvent myEvent=new MyEvent();
        myEvent.setType(2);
        EventBus.getDefault().post(myEvent);
    }

    @Override
    protected void initDatas() {
        messageAdapter = new MessageAdapter();
        messagePresenter.getMyMessage();
        messageAdapter.setOnBtnClickListener(new MessageAdapter.OnBtnClickListener() {
            @Override
            public void clickBtn(int position) {
                DialogUtils.getChangeGuideDialog(MessageActivity.this,messageBean.data.get(position).content,messageBean.data.get(position).items,messageBean.data.get(position).guide);
            }
        });
        xrlMessage.setAdapter(messageAdapter);
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        return;
    }

    @Override
    public String getUserId() {
        return AppState.getInstance().getUserId();
    }

    @Override
    public void setMessageBean(MessageBean messageBean) {
        this.messageBean=messageBean;
        messageAdapter.setMessageBean(messageBean);
    }
}
