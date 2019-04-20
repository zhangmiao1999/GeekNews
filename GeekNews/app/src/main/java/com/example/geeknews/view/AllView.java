package com.example.geeknews.view;

import com.example.geeknews.base.BaseView;

/**
 * Created by 张嘉河 on 2019/4/17.
 */

public interface AllView<T> extends BaseView {
    void onSuccess(T bean);
}
