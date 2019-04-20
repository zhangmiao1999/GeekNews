package com.example.geeknews.ui.gold.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.geeknews.ui.gold.GoldShowBean;

import java.util.ArrayList;

/**
 * Created by 张嘉河 on 2019/4/19.
 *          在viewpager不需要的Fragment需要销毁时,生命周期不一样,
 *         FragmentPagerAdapter:onDestoryView()
 *         FragmentStatePagerAdapter:onDetach();取消关联
 */

public class GoldVpAdapter extends FragmentStatePagerAdapter {
    private final ArrayList<GoldShowBean> mTitles;
    private final ArrayList<Fragment> mFragments;
    private ArrayList<String> mNewTitles = new ArrayList<>();

    public GoldVpAdapter(FragmentManager fm, ArrayList<GoldShowBean> titles, ArrayList<Fragment> fragments) {
        super(fm);
        mTitles = titles;
        mFragments = fragments;

        for (int i = 0; i < mTitles.size(); i++) {
            GoldShowBean bean = mTitles.get(i);
            if (bean.isChecked){
                mNewTitles.add(bean.title);
            }
        }
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
        return mNewTitles.get(position);
    }
}
