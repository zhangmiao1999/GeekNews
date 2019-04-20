package com.example.geeknews.model;

import com.example.geeknews.base.BaseModel;
import com.example.geeknews.base.BaseObserver;
import com.example.geeknews.model.bean.SectionsListBean;
import com.example.geeknews.model.callback.ResultCallBack;
import com.example.geeknews.model.net.ZhihuApiService;
import com.example.geeknews.util.HttpUtils;
import com.example.geeknews.util.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by 张嘉河 on 2019/4/11.
 */

public class SectionsModel extends BaseModel {
    public void initData(final ResultCallBack callBack) {
        HttpUtils.getInstance().getApiserver(ZhihuApiService.mBaseUrl,ZhihuApiService.class)
                .getSectionsList()
                .compose(RxUtils.<SectionsListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<SectionsListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(SectionsListBean sectionsListBean) {
                        callBack.onSuccess(sectionsListBean);
                    }
                });
    }
}
