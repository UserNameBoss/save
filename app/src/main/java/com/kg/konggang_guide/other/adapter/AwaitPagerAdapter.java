package com.kg.konggang_guide.other.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/7/25
 */

public class AwaitPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments=new ArrayList<>();

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    public AwaitPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }


    @Override
    public int getCount() {
        return fragments.size();
    }

}
