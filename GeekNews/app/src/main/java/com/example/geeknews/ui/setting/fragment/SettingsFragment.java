package com.example.geeknews.ui.setting.fragment;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.base.BaseView;
import com.example.geeknews.presenter.setting.SettingPresenter;
import com.example.geeknews.view.SettingsV;

/**
 * Created by 张嘉河 on 2019/4/3.
 */

public class SettingsFragment extends BaseFragment<SettingsV,SettingPresenter> implements BaseView {

    @Override
    protected SettingPresenter initPresenter() {
        return new SettingPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_settings;
    }
}
