package com.example.geeknews.ui.zhihu.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.ui.zhihu.adapter.VpAdapter;
import com.example.geeknews.view.EmptyView;
import com.example.geeknews.view.ZhihuDailyNewsPresenter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 张嘉河 on 2019/4/3.
 */

public class ZhihuDailyNewsFragment extends BaseFragment<EmptyView,ZhihuDailyNewsPresenter> {

    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<Integer> mTabTitle;
    private ArrayList<Fragment> mFragments;
    private VpAdapter mAdapter;



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_daily_news;
    }

    @Override
    protected void initView() {
        super.initView();
        initTabTitle();//初始化tab标题
        initFragment();//初始化Fragment

        mAdapter = new VpAdapter(getChildFragmentManager(),mTabTitle,mFragments);
        mVp.setAdapter(mAdapter);
        mTablayout.setupWithViewPager(mVp);

    }

    @Override
    protected ZhihuDailyNewsPresenter initPresenter() {
        return new ZhihuDailyNewsPresenter();
    }

    private void initTabTitle() {
        mTabTitle = new ArrayList<>();
        mTabTitle.add(R.string.daily);
        mTabTitle.add(R.string.theme);
        mTabTitle.add(R.string.specialcolumn);
        mTabTitle.add(R.string.hot);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new DailyFragment());
        mFragments.add(new ThemeFragment());
        mFragments.add(new SectionsFragment());
        mFragments.add(new HotFragment());
    }
}
