package com.kg.konggang_guide.other.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.bean.MessageBean;
import com.kg.konggang_guide.other.utils.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/7/26
 */

public class MessageAdapter extends XRecyclerView.Adapter<MessageAdapter.ViewHolder> {


    private OnBtnClickListener onBtnClickListener;
    private MessageBean messageBean;

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
        notifyDataSetChanged();
    }

    public void setOnBtnClickListener(OnBtnClickListener onBtnClickListener) {
        this.onBtnClickListener = onBtnClickListener;
    }

    public interface OnBtnClickListener{
        void clickBtn(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, null);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        MessageBean.DataEntity.ListEntity dataEntity=messageBean.data.list.get(position);
        if(dataEntity.items!=null&&dataEntity.items.type==1){
            holder.tvType.setText("改派订单");
            holder.imgIcon.setImageResource(R.mipmap.order_to_order);
            holder.tvDispose.setVisibility(View.VISIBLE);
        }else if(dataEntity.items!=null&&dataEntity.items.type==2){
            holder.tvType.setText("预约订单");
            holder.imgIcon.setImageResource(R.mipmap.booking_order);
            holder.tvDispose.setVisibility(View.INVISIBLE);

        }else if(dataEntity.items!=null&&dataEntity.items.type==3){
            holder.tvType.setText("立即订单");
            holder.tvDispose.setVisibility(View.INVISIBLE);
            holder.tvDispose.setVisibility(View.INVISIBLE);
        }

        holder.tvTime.setText(TimeUtils.getFromTime(dataEntity.publishTime));

        holder.tvContent.setText(dataEntity.content);

        holder.tvDispose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onBtnClickListener!=null){
                    onBtnClickListener.clickBtn(position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return messageBean!=null&&messageBean.data!=null&&messageBean.data.list!=null?
                messageBean.data.list.size():0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_icon)
        ImageView imgIcon;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_dispose)
        TextView tvDispose;
        @BindView(R.id.tv_content)
        TextView tvContent;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
