package com.kg.konggang_guide.other.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kg.konggang_guide.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/7/25
 */

public class AwaitAdapter extends XRecyclerView.Adapter<AwaitAdapter.ViewHolder> {



    private  int Type=0;
    private int prePosition;
    private OnItemClickLintener onItemClickLintener;

    public interface OnItemClickLintener{
        void onClickItem(int position);
    }

    public void setOnItemClickLintener(OnItemClickLintener onItemClickLintener) {
        this.onItemClickLintener = onItemClickLintener;
    }

    public void setType(int type) {
        Type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_await, null);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //待服务
        if(Type==0){
            holder.tvAwaitType.setText("待服务");
            //待接单
        }else{
            holder.tvAwaitType.setText("待接单");
        }

        if(prePosition==position){
            holder.imgLine.setVisibility(View.VISIBLE);
            holder.rl_item.setBackgroundColor(Color.parseColor("#f7fdff"));
        }else{
            holder.imgLine.setVisibility(View.INVISIBLE);
            holder.rl_item.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        holder.rl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prePosition=position;
                notifyDataSetChanged();
                onItemClickLintener.onClickItem(position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return 5;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_awaitType)
        TextView tvAwaitType;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.img_line)
        ImageView imgLine;
        @BindView(R.id.ll_flight)
        LinearLayout ll_flight;
        @BindView(R.id.tv_flight)
        TextView tv_flight;
        @BindView(R.id.rl_item)
        LinearLayout rl_item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
