package com.example.geeknews.model.callback;

/**
 * Created by 张嘉河 on 2019/4/3.
 */

public interface ResultCallBack<T> {

    void onSuccess(T bean);

    void onFail(String message);

}
