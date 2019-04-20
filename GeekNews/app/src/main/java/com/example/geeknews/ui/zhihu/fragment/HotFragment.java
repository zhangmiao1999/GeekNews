package com.example.geeknews.ui.zhihu.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseApplication;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.model.callback.OnItemClick;
import com.example.geeknews.model.bean.HotListBean;
import com.example.geeknews.presenter.zhihu.HotPresenter;
import com.example.geeknews.ui.zhihu.adapter.HotAdapter;
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

public class HotFragment extends BaseFragment<AllView, HotPresenter> implements AllView<HotListBean>, OnRefreshListener {

    private static final String TAG = "HotFragment";
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    private HotAdapter mAdapter;

    @Override
    protected HotPresenter initPresenter() {
        return new HotPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView() {
        mSmart.setRefreshHeader(new MaterialHeader(BaseApplication.getInstance()));
        mSmart.setOnRefreshListener(this);
        mSmart.setEnableAutoLoadmore(false);
        mRlv.setLayoutManager(new LinearLayoutManager(BaseApplication.getInstance()));
        mAdapter = new HotAdapter(BaseApplication.getInstance(),new ArrayList<HotListBean.RecentBean>());
        mRlv.setAdapter(mAdapter);
        //子条目点击事件
        mAdapter.setResultCallBack(new OnItemClick() {
            @Override
            public void OnItemClick(int position) {

            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.initData();
    }

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
    public void onSuccess(HotListBean bean) {
        mAdapter.addData(bean.getRecent());
    }

}
