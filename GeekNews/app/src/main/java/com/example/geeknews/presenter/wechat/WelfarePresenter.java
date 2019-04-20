package com.example.geeknews.presenter.wechat;

import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.model.WelfareModel;
import com.example.geeknews.model.bean.WelfareListBean;
import com.example.geeknews.model.callback.ResultCallBack;
import com.example.geeknews.view.AllView;

/**
 * Created by 张嘉河 on 2019/4/17.
 */

public class WelfarePresenter extends BasePresenter<AllView> implements ResultCallBack<WelfareListBean> {

    private WelfareModel mModel;

    @Override
    protected void initModel() {
        mModel = new WelfareModel();
        mBaseModels.add(mModel);
    }

    public void initData(int page) {
        mModel.initData(page,this);
    }

    @Override
    public void onSuccess(WelfareListBean bean) {
        if (bean!=null&&bean.getResults()!=null&&bean.getResults().size()>0){
            if (mView!=null){
                mView.onSuccess(bean);
            }
        }
    }

    @Override
    public void onFail(String message) {

    }
}
