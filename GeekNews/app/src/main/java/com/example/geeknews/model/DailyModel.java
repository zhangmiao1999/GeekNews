package com.example.geeknews.model;

import android.util.Log;

import com.example.geeknews.base.BaseModel;
import com.example.geeknews.base.BaseObserver;
import com.example.geeknews.model.bean.NewsListBean;
import com.example.geeknews.model.callback.ResultCallBack;
import com.example.geeknews.model.net.ZhihuApiService;
import com.example.geeknews.util.HttpUtils;
import com.example.geeknews.util.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by 张嘉河 on 2019/4/10.
 * 张淼
 */

public class DailyModel extends BaseModel {
    private static final String TAG = "DailyModel";

    public void initBanner(final ResultCallBack callBack) {
        HttpUtils.getInstance().getApiserver(ZhihuApiService.mBaseUrl, ZhihuApiService.class)
                .getDailyList()
                .compose(RxUtils.<NewsListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<NewsListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(NewsListBean hotListBean) {
                        Log.d(TAG, "onNext11: "+hotListBean.toString());
                        callBack.onSuccess(hotListBean);
                    }
                });
    }

    public void initBeforNewsList(String date, final ResultCallBack callBack) {
        HttpUtils.getInstance().getApiserver(ZhihuApiService.mBaseUrl,ZhihuApiService.class)
                .getDailyBeforList(date)
                .compose(RxUtils.<NewsListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<NewsListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(NewsListBean newsListBean) {
                        Log.d(TAG, "onNext: "+newsListBean.toString());
                        callBack.onSuccess(newsListBean);
                    }
                });
    }
}
