package com.example.geeknews.base;

import java.util.ArrayList;

/**
 * Created by 张嘉河 on 2019/4/3.
 */

public abstract class BasePresenter<V extends BaseView> {
    protected V mView;
    protected ArrayList<BaseModel> mBaseModels = new ArrayList<>();

    public void bind(V view) {
        mView = view;
    }

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void onDestroy() {
        mView = null;
        if (mBaseModels.size() > 0) {
            for (BaseModel model : mBaseModels) {
                model.onDestroy();
            }
        }
    }
}
