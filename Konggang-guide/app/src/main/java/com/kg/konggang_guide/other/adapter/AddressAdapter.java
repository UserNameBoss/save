package com.kg.konggang_guide.other.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.services.help.Tip;
import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.bean.AddressBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/7/26
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {


    private AddressBean addressBean;
    private List<Tip> list;
    private int isFrom;
    private OnItemClick onItemClick;
    private ArrayList<String> arrayList=new ArrayList<>();
    private ArrayList<String> arrayIdList=new ArrayList<>();
    private ArrayList<String> arrayListName=new ArrayList<>();

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setIsFrom(int isFrom) {
        this.isFrom = isFrom;
    }

    public void setList(List<Tip> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface OnItemClick{
        void clickItem(int position);
        void deleteItem(int position);
        void clickItemFrom(String address,String airId);
    }

    public void setAddressBean(AddressBean addressBean) {
        this.addressBean = addressBean;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_address, null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(isFrom==1) {
            //AddressBean.DataEntity dataEntity = addressBean.data.get(position);
            holder.tvLocation.setText(arrayListName.get(position));
            holder.tvAddress.setText(arrayList.get(position));
        }else{
            Tip tip=list.get(position);
            holder.tvLocation.setText(tip.getName());
            holder.tvAddress.setText(tip.getAddress());
        }

        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFrom==1){
                    onItemClick.clickItemFrom(arrayList.get(position),arrayIdList.get(position));
                }else {
                    onItemClick.clickItem(position);
                }
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.deleteItem(position);
            }
        });

    }


    @Override
    public int getItemCount() {
        if(isFrom==1) {
            if(addressBean != null && addressBean.data != null) {
                arrayList.clear();
                arrayListName.clear();
                for(int i=0;i<addressBean.data.size();i++){
                    AddressBean.DataEntity dataEntity=addressBean.data.get(i);
                    if(!TextUtils.isEmpty(dataEntity.terminalBuilding)){
                        String[] terminals=addressBean.data.get(i).terminalBuilding.split(",");
                        for(int j=0;j<terminals.length;j++){
                            arrayList.add(dataEntity.airportAddress+dataEntity.airportName+terminals[j]);
                            arrayIdList.add(dataEntity.id+"");
                            arrayListName.add(dataEntity.airportAddress+dataEntity.airportName);
                        }
                    }
                }
            }
            return arrayList.size();
        }else{
            return list!=null?list.size():0;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.img_delete)
        ImageView imgDelete;
        @BindView(R.id.ll_item)
        LinearLayout ll_item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
