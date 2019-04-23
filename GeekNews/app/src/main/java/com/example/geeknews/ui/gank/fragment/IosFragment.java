package com.example.geeknews.ui.gank.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.geeknews.R;
import com.example.geeknews.base.BaseApplication;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.model.bean.GankItemBean;
import com.example.geeknews.presenter.gank.AndroidPresenter;
import com.example.geeknews.ui.gank.adapter.AndroidRvAdapter;
import com.example.geeknews.ui.gank.adapter.IosRvAdapter;
import com.example.geeknews.util.SystemUtil;
import com.example.geeknews.view.AllView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by 张嘉河 on 2019/4/17.
 */

public class IosFragment extends BaseFragment<AllView,AndroidPresenter> implements AllView<GankItemBean> {

    @BindView(R.id.iv_tech_blur)
    ImageView mIvTechBlur;
    @BindView(R.id.iv_tech_origin)
    ImageView mIvTechOrigin;
    @BindView(R.id.tv_tech_copyright)
    TextView mTvTechCopyright;
    @BindView(R.id.tech_appbar)
    AppBarLayout mTechAppbar;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmartRefreshLayout;
    private String mTech = "iOS";
    private int mNum = 10;
    private int mPage = 1;
    private IosRvAdapter mAdapter;

    @Override
    protected AndroidPresenter initPresenter() {
        return new AndroidPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank_android;
    }

    @Override
    protected void initView() {
        RequestOptions options = new RequestOptions()
                .transform(new BlurTransformation(getContext()));
        Glide.with(getContext()).load(R.drawable.c).apply(options).into(mIvTechBlur);
        Glide.with(getContext()).load(R.drawable.c).into(mIvTechOrigin);

        //appbar 滑动偏移监听
        mTechAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //两张图片,后面的是高斯模糊过的,前面的是原图,滑动过程中修改原图
                //的透明度,实现效果
                ///透明度值范围,0到1,0完全透明,1完全不透明
                //1dp = 3px(我的手机) 768px
                int imgHeight = SystemUtil.dp2px(256);
                //verticalOffset 0到-768
                //verticalOffset / imgHeight(768)范围:0到-1
                float rate = 1 + verticalOffset * 2.0f / imgHeight;
                if (rate >= 0) {
                    mIvTechOrigin.setAlpha(rate);
                }

            }
        });

        mSmartRefreshLayout.setRefreshHeader(new MaterialHeader(BaseApplication.getInstance()));
        mSmartRefreshLayout.setRefreshFooter(new BallPulseFooter(BaseApplication.getInstance()));
        mSmartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
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
        });

        mRv.setLayoutManager(new LinearLayoutManager(BaseApplication.getInstance()));
        mAdapter = new IosRvAdapter(new ArrayList<GankItemBean.ResultsBean>());
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.initData(mTech, mNum, mPage);
    }

    @Override
    public void onSuccess(GankItemBean bean) {
        mAdapter.setData(bean.getResults());
        mSmartRefreshLayout.finishLoadmore();
        mSmartRefreshLayout.finishRefresh();
    }
}
