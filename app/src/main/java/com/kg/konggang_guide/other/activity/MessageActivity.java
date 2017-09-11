package com.kg.konggang_guide.other.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.adapter.MessageAdapter;
import com.kg.konggang_guide.other.base.CpBaseActivty;
import com.kg.konggang_guide.other.utils.DialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity extends CpBaseActivty {


    @BindView(R.id.xrl_message)
    XRecyclerView xrlMessage;
    private MessageAdapter messageAdapter;

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
        supportTitleStatus(true);
        title.setTitleText("消息中心");
        title.setLeftImage(R.mipmap.app_back);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrlMessage.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {
        messageAdapter = new MessageAdapter();
        messageAdapter.setOnBtnClickListener(new MessageAdapter.OnBtnClickListener() {
            @Override
            public void clickBtn(int position) {
                DialogUtils.getChangeGuideDialog(MessageActivity.this);
            }
        });
        xrlMessage.setAdapter(messageAdapter);
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        return;
    }
}
