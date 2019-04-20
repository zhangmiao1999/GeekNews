package com.example.geeknews.model.net;

import com.example.geeknews.model.bean.WechatListBean;
import com.example.geeknews.model.bean.WelfareListBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by 张嘉河 on 2019/4/17.
 */

public interface WechatApiService {

    String mBaseUrl = "http://api.tianapi.com/";

    /**
     * 微信精选
     * @param map
     * @return
     */
    @GET("wxnew/?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1")
    Observable<WechatListBean> getWechatList(@QueryMap Map<String,Object> map);


    String mWalfareUrl = "http://gank.io/api/";
    /**
     * 福利
     * @param page
     * @return
     */

    @GET("data/%E7%A6%8F%E5%88%A9/10/{page}")

    Observable<WelfareListBean> getWelfareListBean(@Path("page") int page);
}
