package com.example.geeknews.ui.vtex.fragment;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.presenter.vtex.V2exChildPresenter;
import com.example.geeknews.view.AllView;

/**
 * Created by 张嘉河 on 2019/4/19.
 */

public class V2exChildFragment extends BaseFragment<AllView,V2exChildPresenter> implements AllView {

    @Override
    protected V2exChildPresenter initPresenter() {
        return new V2exChildPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2ex_child;
    }

    @Override
    public void onSuccess(Object bean) {

    }
}
