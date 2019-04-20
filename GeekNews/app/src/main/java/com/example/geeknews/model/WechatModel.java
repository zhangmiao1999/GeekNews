package com.example.geeknews.model;

import com.example.geeknews.base.BaseModel;
import com.example.geeknews.base.BaseObserver;
import com.example.geeknews.model.bean.WechatListBean;
import com.example.geeknews.model.callback.ResultCallBack;
import com.example.geeknews.model.net.WechatApiService;
import com.example.geeknews.util.HttpUtils;
import com.example.geeknews.util.RxUtils;

import java.util.HashMap;

import io.reactivex.disposables.Disposable;

/**
 * Created by 张嘉河 on 2019/4/17.
 */

public class WechatModel extends BaseModel {
    public void initData(int page, final ResultCallBack callBack) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("key","52b7ec3471ac3bec6846577e79f20e4c");
        map.put("num","10");
        map.put("page",page);
        HttpUtils.getInstance().getApiserver(WechatApiService.mBaseUrl,WechatApiService.class)
                .getWechatList(map)
                .compose(RxUtils.<WechatListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WechatListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WechatListBean wechatListBean) {
                        callBack.onSuccess(wechatListBean);
                    }
                });
    }
}
