package com.example.geeknews.ui.about.fragment;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.base.BaseView;
import com.example.geeknews.presenter.about.AboutPresenter;
import com.example.geeknews.view.AboutV;

/**
 * Created by 张嘉河 on 2019/4/3.
 */

public class AboutFragment extends BaseFragment<AboutV,AboutPresenter> implements BaseView {

    @Override
    protected AboutPresenter initPresenter() {
        return new AboutPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }
}
