package com.example.geeknews.presenter.zhihu;

import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.model.SectionsModel;
import com.example.geeknews.model.bean.SectionsListBean;
import com.example.geeknews.model.callback.ResultCallBack;
import com.example.geeknews.view.AllView;

/**
 * Created by 张嘉河 on 2019/4/11.
 */

public class SectionsPresenter extends BasePresenter<AllView> {

    private SectionsModel mModel;

    @Override
    protected void initModel() {
        mModel = new SectionsModel();
        mBaseModels.add(mModel);
    }

    public void initData() {
        mModel.initData(new ResultCallBack<SectionsListBean>() {
            @Override
            public void onSuccess(SectionsListBean bean) {
                if (bean!=null&&bean.getData()!=null&&bean.getData().size()>0){
                    if (mView!=null){
                        mView.onSuccess(bean);
                    }
                }
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
