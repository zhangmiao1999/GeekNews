package com.example.geeknews.model;

import com.example.geeknews.base.BaseModel;
import com.example.geeknews.base.BaseObserver;
import com.example.geeknews.model.bean.WelfareListBean;
import com.example.geeknews.model.callback.ResultCallBack;
import com.example.geeknews.model.net.WechatApiService;
import com.example.geeknews.util.HttpUtils;
import com.example.geeknews.util.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by 张嘉河 on 2019/4/17.
 */

public class WelfareModel extends BaseModel {
    public void initData(int page, final ResultCallBack callBack) {
        HttpUtils.getInstance().getApiserver(WechatApiService.mWalfareUrl,WechatApiService.class)
                .getWelfareListBean(page)
                .compose(RxUtils.<WelfareListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WelfareListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WelfareListBean welfareListBean) {
                        callBack.onSuccess(welfareListBean);
                    }
                });
    }
}
