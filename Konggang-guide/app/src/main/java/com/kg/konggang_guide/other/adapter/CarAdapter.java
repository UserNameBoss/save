package com.kg.konggang_guide.other.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kg.konggang_guide.MainApplication;
import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.bean.DriverListBean;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/7/25
 */

public class CarAdapter extends XRecyclerView.Adapter<CarAdapter.ViewHolder> {


    private OnClickCar onClickCar;
    private DriverListBean driverListBean;

    public void setDriverListBean(DriverListBean driverListBean) {
        this.driverListBean = driverListBean;
        notifyDataSetChanged();
    }

    public void setOnClickCar(OnClickCar onClickCar) {
        this.onClickCar = onClickCar;
    }

    public interface OnClickCar{
        void toCar(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, null);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(layoutParams);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        DriverListBean.DataEntity dataEntity=driverListBean.data.get(position);

        if(!TextUtils.isEmpty(dataEntity.picture)) {
            Picasso.with(MainApplication.applicationContext).load(dataEntity.picture).error(R.mipmap.app_user_icon).into(holder.imgCarIcon);
        }

        holder.tvName.setText(dataEntity.driverName);
        holder.tvCarLocation.setText(dataEntity.parkPosition);
        holder.tvCarCode.setText(dataEntity.licensePlate);

        holder.tvToCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCar.toCar(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return driverListBean!=null&&driverListBean.data!=null?driverListBean.data.size():0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_carIcon)
        ImageView imgCarIcon;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_carLocation)
        TextView tvCarLocation;
        @BindView(R.id.tv_carCode)
        TextView tvCarCode;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_ToCar)
        TextView tvToCar;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
