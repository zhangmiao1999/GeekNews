package com.example.geeknews.ui.zhihu.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseApplication;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.model.bean.SectionsListBean;
import com.example.geeknews.presenter.zhihu.SectionsPresenter;
import com.example.geeknews.ui.zhihu.adapter.SectionsAdapter;
import com.example.geeknews.view.AllView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 张嘉河 on 2019/4/9.
 */

public class SectionsFragment extends BaseFragment<AllView, SectionsPresenter> implements AllView<SectionsListBean>, OnRefreshListener {

    private static final String TAG = "SectionsFragment";
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    private SectionsAdapter mAdapter;

    @Override
    protected SectionsPresenter initPresenter() {
        return new SectionsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sections;
    }

    @Override
    protected void initView() {
        mSmart.setRefreshHeader(new MaterialHeader(BaseApplication.getInstance()));
        mSmart.setOnRefreshListener(this);
        mSmart.setEnableAutoLoadmore(false);
        mRlv.setLayoutManager(new GridLayoutManager(BaseApplication.getInstance(),2));
        mAdapter = new SectionsAdapter(BaseApplication.getInstance(),new ArrayList<SectionsListBean.DataBean>());
        mRlv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.initData();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh(1000);
    }

    @Override
    public void onSuccess(SectionsListBean bean) {
        mAdapter.addData(bean.getData());
    }
}
