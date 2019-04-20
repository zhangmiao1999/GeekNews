package com.example.geeknews.ui.vtex.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.model.bean.V2exTabBean;

import java.util.ArrayList;

/**
 * Created by 张嘉河 on 2019/4/19.
 */

public class V2exVpAdapter extends FragmentPagerAdapter {
    private final ArrayList<V2exTabBean> mList;
    private final ArrayList<BaseFragment> mFragments;

    public V2exVpAdapter(FragmentManager fm, ArrayList<V2exTabBean> list, ArrayList<BaseFragment> fragments) {
        super(fm);
        mList = list;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).tab;
    }
}
