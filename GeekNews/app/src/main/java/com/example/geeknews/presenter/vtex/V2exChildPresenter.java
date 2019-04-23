package com.example.geeknews.presenter.vtex;

import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.model.V2exChildModel;
import com.example.geeknews.model.bean.V2exListBean;
import com.example.geeknews.model.callback.ResultCallBack;
import com.example.geeknews.view.AllView;

/**
 * Created by 张嘉河 on 2019/4/19.
 */

public class V2exChildPresenter extends BasePresenter<AllView> {

    private V2exChildModel mModel;

    @Override
    protected void initModel() {
        mModel = new V2exChildModel();
        mBaseModels.add(mModel);
    }

    public void initData(String linkHref) {
        mModel.initData(linkHref, new ResultCallBack<V2exListBean>() {
            @Override
            public void onSuccess(V2exListBean bean) {
                if (bean!=null){
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
