package com.kg.konggang_guide.personal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.utils.TimeUtils;
import com.kg.konggang_guide.personal.bean.MyOrderBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/7/27
 */

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {


    private MyOrderBean myOrderBean;
    private ArrayList<Integer> listMonth=new ArrayList<>();
    private HashMap<Integer,String> hashMapNow=new HashMap<>();
    private HashMap<Integer,String> hashMapReservation=new HashMap<>();
    private int currMonth;

    public MyOrderAdapter() {
        currMonth= TimeUtils.getCurrMonth();
    }

    public void setMyOrderBean(MyOrderBean myOrderBean) {
        this.myOrderBean = myOrderBean;

        if(myOrderBean!=null&&myOrderBean.data!=null){
            if(myOrderBean.data.list1!=null){
                for(int i=0;i<myOrderBean.data.list1.size();i++){
                    MyOrderBean.DataEntity.List1Entity list1Entity=myOrderBean.data.list1.get(i);
                    listMonth.add(list1Entity.month);
                    hashMapNow.put(list1Entity.month,list1Entity.number+"");
                }
            }

            if(myOrderBean.data.list2!=null){
                for(int i=0;i<myOrderBean.data.list2.size();i++){
                    MyOrderBean.DataEntity.List2Entity list2Entity=myOrderBean.data.list2.get(i);
                    if(!listMonth.contains(list2Entity.month)) {
                        listMonth.add(list2Entity.month);
                    }
                    hashMapReservation.put(list2Entity.month,list2Entity.number+"");
                }
            }
        }
        Collections.sort(listMonth,Collections.reverseOrder());
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_myorder, null);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int month=listMonth.get(position);
        if(hashMapNow.containsKey(month)) {
            holder.tvNowPrice.setText(hashMapNow.get(month));
        }else{
            holder.tvNowPrice.setText("0");
        }

        if(hashMapReservation.containsKey(month)) {
            holder.tvReservationPrice.setText(hashMapReservation.get(month));
        }else{
            holder.tvReservationPrice.setText("0");
        }

        if(currMonth==month) {
            holder.tvTime.setText("当月");
        }else{
            holder.tvTime.setText(month + "月");
        }

    }


    @Override
    public int getItemCount() {
        return listMonth.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_nowPrice)
        TextView tvNowPrice;
        @BindView(R.id.tv_reservationPrice)
        TextView tvReservationPrice;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
