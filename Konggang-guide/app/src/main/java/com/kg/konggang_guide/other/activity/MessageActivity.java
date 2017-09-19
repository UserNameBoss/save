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
    private int pageNumber=1;

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
        supportTitle(true);
        title.setTitleText("消息中心");
        title.setLeftImage(R.mipmap.app_back);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrlMessage.setLayoutManager(linearLayoutManager);
        messagePresenter=new MessagePresenter(this);
       // xrlMessage.setPullRefreshEnabled(false);
        xrlMessage.setLoadingMoreEnabled(true);
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
        xrlMessage.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pageNumber=1;
                messagePresenter.getMyMessage();
                //messageBean=null;
            }

            @Override
            public void onLoadMore() {
                pageNumber++;
                messagePresenter.getMyMessage();
            }
        });
        messageAdapter.setOnBtnClickListener(new MessageAdapter.OnBtnClickListener() {
            @Override
            public void clickBtn(int position) {
                DialogUtils.getChangeGuideDialog(MessageActivity.this,messageBean.data.list.get(position).content,messageBean.data.list.get(position).items,messageBean.data.list.get(position).guide);
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
    public String getPageNumber() {
        return pageNumber+"";
    }

    @Override
    public void setIsSuccess(boolean isSuccess) {
        if(isSuccess){

        }else{
            if(pageNumber>1) {
                pageNumber--;
            }
        }
        xrlMessage.loadMoreComplete();
        xrlMessage.refreshComplete();
    }

    @Override
    public void setMessageBean(MessageBean messageBean) {
        if(messageBean!=null&&messageBean.data!=null) {
            System.out.println("======"+messageBean+"======"+pageNumber);
            if(this.messageBean==null||pageNumber==1) {
                this.messageBean = messageBean;
            }else{
                if(messageBean.data.list!=null){
                    this.messageBean.data.list.addAll(messageBean.data.list);
                }
            }
            messageAdapter.setMessageBean(this.messageBean);
        }
    }
}
