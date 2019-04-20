package com.example.geeknews.ui.gold.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.constant.Constants;
import com.example.geeknews.presenter.EmptyPresenter;
import com.example.geeknews.view.EmptyView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张嘉河 on 2019/4/19.
 */

public class GoldDetailFragment extends BaseFragment<EmptyView, EmptyPresenter> implements EmptyView {
    @BindView(R.id.tv)
    TextView mTv;

    public static GoldDetailFragment newInstance(String text) {

        GoldDetailFragment fragment = new GoldDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.DATA,text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold_detail;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        String data = arguments.getString(Constants.DATA);
        mTv.setText(data);
    }
}
