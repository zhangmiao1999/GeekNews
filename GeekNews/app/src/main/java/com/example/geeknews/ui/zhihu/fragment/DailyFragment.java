package com.example.geeknews.ui.zhihu.fragment;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseApplication;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.model.bean.NewsListBean;
import com.example.geeknews.presenter.zhihu.DailyPresenter;
import com.example.geeknews.ui.zhihu.activity.CalendarActivity;
import com.example.geeknews.ui.zhihu.adapter.DailyAdapter;
import com.example.geeknews.view.DailyView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 张嘉河 on 2019/4/9.
 */

public class DailyFragment extends BaseFragment<DailyView, DailyPresenter> implements DailyView, OnRefreshListener {

    private static final String TAG = "DailyFragment";
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    @BindView(R.id.floatbutton)
    FloatingActionButton mButton;
    private DailyAdapter mAdapter;
    private String mDate;

    @Override
    protected DailyPresenter initPresenter() {
        return new DailyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        mSmart.setRefreshHeader(new MaterialHeader(BaseApplication.getInstance()));
        mSmart.setOnRefreshListener(this);
        mSmart.setEnableAutoLoadmore(false);
        mRlv.setLayoutManager(new LinearLayoutManager(BaseApplication.getInstance()));

        mAdapter = new DailyAdapter(new ArrayList<NewsListBean.TopStoriesBean>(), new ArrayList<NewsListBean.StoriesBean>(), getContext());
        mRlv.setAdapter(mAdapter);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CalendarActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void initData() {
        mPresenter.initNewsList();
        mPresenter.initBeforNewsList(mDate);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDate(String time){
        mDate = time;
    };

    /**
     * 下拉刷新
     *
     * @param refreshlayout
     */
    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh(1000);
    }


    @Override
    public void onSuccess2(NewsListBean bean) {
        mAdapter.addData(bean);
    }

    @Override
    public void onSuccess(NewsListBean bean) {
        mAdapter.addData(bean);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
