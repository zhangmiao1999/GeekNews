package com.example.geeknews.ui.gank.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseApplication;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.model.bean.WelfareListBean;
import com.example.geeknews.presenter.wechat.WelfarePresenter;
import com.example.geeknews.ui.gank.adapter.WelfareAdapter;
import com.example.geeknews.view.AllView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 张嘉河 on 2019/4/17.
 */

public class WelfareFragment extends BaseFragment<AllView, WelfarePresenter> implements AllView<WelfareListBean>, OnRefreshLoadmoreListener {

    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    private WelfareAdapter mAdapter;
    private int mPage = 1;

    @Override
    protected WelfarePresenter initPresenter() {
        return new WelfarePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_welfare;
    }

    @Override
    protected void initView() {
        mSmart.setRefreshHeader(new MaterialHeader(BaseApplication.getInstance()));
        mSmart.setEnableLoadmore(false);
        mSmart.setOnRefreshLoadmoreListener(this);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //设置 GapStrategy 为 GAP_HANDLING_NONE:这样可以防止图片在上下滑动的时候移动.
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRlv.setLayoutManager(manager);

        mAdapter = new WelfareAdapter(new ArrayList<WelfareListBean.ResultsBean>());
        mRlv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.initData(mPage);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mPage++;
        initData();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPage = 1;
        mAdapter.mBeans.clear();
        initData();
    }

    @Override
    public void onSuccess(WelfareListBean bean) {
        mAdapter.setData(bean.getResults());
        mSmart.finishRefresh();
        mSmart.finishLoadmore();
    }
}
