package com.example.geeknews.view;

import com.example.geeknews.base.BaseView;
import com.example.geeknews.model.bean.NewsListBean;

/**
 * Created by 张嘉河 on 2019/4/18.
 */

public interface DailyView extends BaseView{
    void onSuccess2(NewsListBean bean);

    void onSuccess(NewsListBean bean);
}
