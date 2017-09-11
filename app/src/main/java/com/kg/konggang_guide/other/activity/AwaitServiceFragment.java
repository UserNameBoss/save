package com.kg.konggang_guide.other.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.adapter.AwaitAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AwaitServiceFragment extends Fragment {


    @BindView(R.id.xrview)
    XRecyclerView xrview;
    Unbinder unbinder;

    private AwaitAdapter awaitAdapter;
    private int type=0;
    private OnFragmentData onFragmentData;

    public void setType(int type) {
        this.type = type;
    }

    public interface OnFragmentData{
        void itemClick(int position,int type);
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
        initView();
        return view;
    }

    private void initView(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrview.setLayoutManager(linearLayoutManager);
        xrview.setPullRefreshEnabled(false);
        awaitAdapter=new AwaitAdapter();
        awaitAdapter.setType(type);
        awaitAdapter.setOnItemClickLintener(new AwaitAdapter.OnItemClickLintener() {
            @Override
            public void onClickItem(int position) {
                if(onFragmentData!=null){
                    onFragmentData.itemClick(position,type);
                }
            }
        });
        xrview.setAdapter(awaitAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
