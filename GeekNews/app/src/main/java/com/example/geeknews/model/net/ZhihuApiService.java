package com.example.geeknews.model.net;

import com.example.geeknews.model.bean.NewsListBean;
import com.example.geeknews.model.bean.HotListBean;
import com.example.geeknews.model.bean.SectionsListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 张嘉河 on 2019/4/10.
 */

public interface ZhihuApiService {
    String mBaseUrl = "http://news-at.zhihu.com/api/4/";

    /**
     * 知乎Banner
     * http://news-at.zhihu.com/api/4/news/latest
     *
     * @return
     */
    @GET("news/latest")
    Observable<NewsListBean> getDailyList();

    @GET("news/before/{date}")
    Observable<NewsListBean> getDailyBeforList(@Path("date") String date);

    /**
     * 专栏日报
     *
     * @return
     */
    @GET("sections")
    Observable<SectionsListBean> getSectionsList();

    /**
     *
     *6)专栏日报详情
     section/{id} 参数id上一个接口中
     * @return
     */
    @GET("section/{id}")
    Observable getDetailsSectionsList();

    /**
     * 热门列表
     *
     * @return
     */
    @GET("news/hot")
    Observable<HotListBean> getHotList();
}
