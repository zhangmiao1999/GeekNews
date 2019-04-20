package com.example.geeknews.presenter.wechat;

import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.model.WechatModel;
import com.example.geeknews.model.bean.WechatListBean;
import com.example.geeknews.model.callback.ResultCallBack;
import com.example.geeknews.view.AllView;

/**
 * Created by 张嘉河 on 2019/4/3.
 */

public class WechatPresenter extends BasePresenter<AllView> implements ResultCallBack<WechatListBean> {

    private WechatModel mModel;

    @Override
    protected void initModel() {
        mModel = new WechatModel();
        mBaseModels.add(mModel);
    }

    public void initData(int page) {
        mModel.initData(page,this);
        mBaseModels.add(mModel);
    }


    @Override
    public void onSuccess(WechatListBean bean) {
        if(bean!=null&& bean.getNewslist()!=null&& bean.getNewslist().size()>0){
            if (mView!=null){
                mView.onSuccess(bean);
            }
        }
    }

    @Override
    public void onFail(String message) {

    }
}
