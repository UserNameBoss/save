package com.kg.konggang_guide.other.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kg.konggang_guide.AppConstants;
import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.adapter.AwaitAdapter;
import com.kg.konggang_guide.other.base.CpBaseFragment;
import com.kg.konggang_guide.other.bean.OrderBean;
import com.kg.konggang_guide.other.event.OrderEvent;
import com.kg.konggang_guide.other.presenter.AwaitServicePresenter;
import com.kg.konggang_guide.other.view.IAwaitView;
import com.kg.konggang_guide.personal.AppState;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * A simple {@link Fragment} subclass.
 */
public class AwaitServiceFragment extends CpBaseFragment implements IAwaitView{


    @BindView(R.id.xrview)
    XRecyclerView xrview;
    Unbinder unbinder;

    private AwaitAdapter awaitAdapter;
    //0:待服务 1：待接单
    private int type=0;
    private OnFragmentData onFragmentData;
    private AwaitServicePresenter awaitServicePresenter;
    private OrderBean orderBean;
    private int pageNum=1;

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String getGuideId() {
        return AppState.getInstance().getUserId();
    }

    @Override
    public String getType() {
        if(type==0) {
            return "3";
        }else{
            return "1";
        }
    }

    @Override
    public String getPageNum() {
        return pageNum+"";
    }

    @Override
    public void isSuccess(int code) {
        if(code!= AppConstants.SUCCESS_CODE) {
            if(pageNum>1) {
                pageNum--;
            }
        }
        xrview.loadMoreComplete();
        xrview.refreshComplete();
    }

    @Override
    public void setOrderBean(OrderBean orderBean) {
        if(this.orderBean==null||this.orderBean.data==null||this.orderBean.data.list==null||pageNum==1) {
            this.orderBean = orderBean;
            awaitAdapter.setOrderBean(orderBean);
        }else{
           if(orderBean.data.list!=null&&orderBean.data.list.size()>0){
               this.orderBean.data.list.addAll(orderBean.data.list);
           }
        }
        xrview.loadMoreComplete();

    }

    public interface OnFragmentData{
        void itemClick(int position, int type,OrderBean.DataEntity.ListEntity dataEntity);
    }

    public void setOnFragmentData(OnFragmentData onFragmentData) {
        this.onFragmentData = onFragmentData;
    }

    public AwaitServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_await, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initView();
        return view;
    }

    @Override
    public View onCreateRootView(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    private void initView(){
        awaitServicePresenter=new AwaitServicePresenter(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrview.setLayoutManager(linearLayoutManager);
        xrview.setPullRefreshEnabled(true);
        xrview.setLoadingMoreEnabled(true);
        xrview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pageNum=1;
                awaitServicePresenter.getOrderList();
            }

            @Override
            public void onLoadMore() {
                pageNum++;
                awaitServicePresenter.getOrderList();
            }
        });
        awaitAdapter=new AwaitAdapter();
        awaitAdapter.setType(type);
        awaitServicePresenter.getOrderList();
        awaitAdapter.setOnItemClickLintener(new AwaitAdapter.OnItemClickLintener() {
            @Override
            public void onClickItem(int position) {
                if(onFragmentData!=null){
                    onFragmentData.itemClick(position,type,orderBean.data.list.get(position));
                }
            }
        });
        xrview.setAdapter(awaitAdapter);
    }

    @Subscribe
    public void onEventMainThread(OrderEvent orderEvent){
        if(orderEvent.getIsRefresh()){
            if(type==1){
                pageNum=1;
                awaitServicePresenter.getOrderList();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    public void refreshData(){
        awaitAdapter.setOrderBean(null);
        awaitAdapter.setPrePosition(0);
        this.orderBean=null;
        pageNum=1;
        awaitServicePresenter.getOrderList();
    }



}
