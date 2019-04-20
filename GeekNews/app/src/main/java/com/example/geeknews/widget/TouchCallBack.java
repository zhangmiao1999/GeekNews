package com.example.geeknews.widget;

/**
 * Created by 张嘉河 on 2019/4/19.
 */

public interface TouchCallBack {
    //数据交换
    void onItemMove(int fromPosition, int toPosition);
    //数据移除
    void onItemDelete(int position);
}
