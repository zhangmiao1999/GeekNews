package com.example.geeknews.ui.wechat.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseApplication;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.model.bean.WechatListBean;
import com.example.geeknews.presenter.wechat.WechatPresenter;
import com.example.geeknews.ui.wechat.adapter.WechatAdapter;
import com.example.geeknews.view.AllView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 张嘉河 on 2019/4/3.
 * 张淼
 */

public class WechatFragment extends BaseFragment<AllView, WechatPresenter> implements AllView<WechatListBean>, OnRefreshLoadmoreListener {

    private static final String TAG = "WechatFragment";
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    private WechatAdapter mAdapter;
    private int mPage = 1;

    @Override
    protected WechatPresenter initPresenter() {
        return new WechatPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initView() {
        mSmart.setRefreshHeader(new MaterialHeader(BaseApplication.getInstance()));
        mSmart.setRefreshFooter(new BallPulseFooter(BaseApplication.getInstance()));
        mSmart.setOnRefreshLoadmoreListener(this);
        mRlv.setLayoutManager(new LinearLayoutManager(BaseApplication.getInstance()));
        mAdapter = new WechatAdapter(new ArrayList<WechatListBean.NewslistBean>());
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
    public void onSuccess(WechatListBean bean) {
        Log.d(TAG, "onSuccess: "+bean.toString());
        mAdapter.setData(bean.getNewslist());
        mSmart.finishLoadmore();
        mSmart.finishRefresh();
    }
}
