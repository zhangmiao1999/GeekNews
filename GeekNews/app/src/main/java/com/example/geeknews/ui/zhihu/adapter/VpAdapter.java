package com.example.geeknews.ui.zhihu.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.geeknews.base.BaseApplication;

import java.util.ArrayList;

/**
 * Created by 张嘉河 on 2019/4/9.
 */

public class VpAdapter extends FragmentPagerAdapter {
    private final ArrayList<Integer> mTabTitle;
    private final ArrayList<Fragment> mFragments;

    public VpAdapter(FragmentManager fm, ArrayList<Integer> tabTitle, ArrayList<Fragment> fragments) {
        super(fm);
        mTabTitle = tabTitle;
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
        return BaseApplication.getInstance().getString(mTabTitle.get(position));
    }
}
