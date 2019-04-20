package com.example.geeknews.model;

import com.example.geeknews.base.BaseModel;
import com.example.geeknews.base.BaseObserver;
import com.example.geeknews.model.callback.ResultCallBack;
import com.example.geeknews.model.net.ZhihuApiService;
import com.example.geeknews.model.bean.HotListBean;
import com.example.geeknews.util.HttpUtils;
import com.example.geeknews.util.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by 张嘉河 on 2019/4/11.
 */

public class HotModel extends BaseModel {
    public void initData(final ResultCallBack callBack) {
        ZhihuApiService apiservice = HttpUtils.getInstance().getApiserver(ZhihuApiService.mBaseUrl, ZhihuApiService.class);
        apiservice.getHotList()
                .compose(RxUtils.<HotListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HotListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(HotListBean hotListBean) {
                        callBack.onSuccess(hotListBean);
                    }
                });
    }
}
