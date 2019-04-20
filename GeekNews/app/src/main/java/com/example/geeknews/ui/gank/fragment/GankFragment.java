package com.example.geeknews.ui.gank.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.base.BaseView;
import com.example.geeknews.presenter.gank.GankPresenter;
import com.example.geeknews.ui.zhihu.adapter.VpAdapter;
import com.example.geeknews.view.GankV;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 张嘉河 on 2019/4/3.
 */

public class GankFragment extends BaseFragment<GankV, GankPresenter> implements BaseView {

    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<Integer> mTabTitle;
    private ArrayList<Fragment> mFragments;
    private VpAdapter mAdapter;

    @Override
    protected GankPresenter initPresenter() {
        return new GankPresenter();
    }

    //这里布局和第一个页面一样，直接复用了
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_daily_news;
    }

    @Override
    protected void initView() {

        initTabTitle();//初始化tab标题
        initFragment();//初始化Fragment
        //这里ViewPager也复用Zhihu的
        mAdapter = new VpAdapter(getChildFragmentManager(),mTabTitle,mFragments);
        mVp.setAdapter(mAdapter);
        mTablayout.setupWithViewPager(mVp);
    }

    private void initTabTitle() {
        mTabTitle = new ArrayList<>();
        mTabTitle.add(R.string.android);
        mTabTitle.add(R.string.ios);
        mTabTitle.add(R.string.front);
        mTabTitle.add(R.string.welfare);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new AndroidFragment());
        mFragments.add(new IosFragment());
        mFragments.add(new FrontFragment());
        mFragments.add(new WelfareFragment());
    }
}
