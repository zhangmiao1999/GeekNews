package com.example.geeknews.ui.collect.fragment;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.base.BaseView;
import com.example.geeknews.presenter.collect.CollectPresenter;
import com.example.geeknews.view.CollectV;

/**
 * Created by 张嘉河 on 2019/4/3.
 */

public class CollectFragment extends BaseFragment<CollectV,CollectPresenter> implements BaseView {

    @Override
    protected CollectPresenter initPresenter() {
        return new CollectPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }
}
