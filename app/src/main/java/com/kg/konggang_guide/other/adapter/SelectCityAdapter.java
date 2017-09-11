package com.kg.konggang_guide.other.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kg.konggang_guide.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/7/27
 */

public class SelectCityAdapter extends BaseAdapter {



    private ArrayList<String> cityList = new ArrayList<>();


    public void setCityList(ArrayList<String> cityList) {
        this.cityList = cityList;
    }

    @Override
    public int getCount() {
        return cityList.size();
    }


    @Override
    public Object getItem(int position) {
        return cityList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null) {
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tvCity.setText(cityList.get(position));
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_city)
        TextView tvCity;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
