package com.example.geeknews.presenter.zhihu;

import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.model.callback.ResultCallBack;
import com.example.geeknews.model.HotModel;
import com.example.geeknews.model.bean.HotListBean;
import com.example.geeknews.view.AllView;

/**
 * Created by 张嘉河 on 2019/4/11.
 */

public class HotPresenter extends BasePresenter<AllView> {

    private HotModel mModel;

    @Override
    protected void initModel() {
        mModel = new HotModel();
        mBaseModels.add(mModel);
    }

    public void initData() {
        mModel.initData(new ResultCallBack<HotListBean>() {
            @Override
            public void onSuccess(HotListBean bean) {
                if (bean!=null&&bean.getRecent()!=null&&bean.getRecent().size()>0){
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
