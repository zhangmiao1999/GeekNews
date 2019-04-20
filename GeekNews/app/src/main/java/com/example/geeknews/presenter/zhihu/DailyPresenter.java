package com.example.geeknews.presenter.zhihu;

import android.util.Log;

import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.model.bean.NewsListBean;
import com.example.geeknews.model.callback.ResultCallBack;
import com.example.geeknews.model.DailyModel;
import com.example.geeknews.view.DailyView;

/**
 * Created by 张嘉河 on 2019/4/10.
 */

public class DailyPresenter extends BasePresenter<DailyView>  {

    private static final String TAG = "DailyPresenter";
    private DailyModel mModel;

    @Override
    protected void initModel() {
        mModel = new DailyModel();
        mBaseModels.add(mModel);
    }

    public void initNewsList() {
        mModel.initBanner(new ResultCallBack<NewsListBean>() {
            @Override
            public void onSuccess(NewsListBean bean) {
                if (bean != null && bean.getTop_stories() != null && bean.getTop_stories().size() > 0) {
                    if (mView != null) {
                        mView.onSuccess(bean);
                    }
                } else {
                    Log.d(TAG, "onSuccess: " + "数据为空");
                }
            }

            @Override
            public void onFail(String message) {

            }
        });
    }


    public void initBeforNewsList(String date) {
        mModel.initBeforNewsList(date, new ResultCallBack<NewsListBean>() {
            @Override
            public void onSuccess(NewsListBean bean) {
                if (bean!=null&& bean.getStories()!=null&& bean.getStories().size()>0){
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
