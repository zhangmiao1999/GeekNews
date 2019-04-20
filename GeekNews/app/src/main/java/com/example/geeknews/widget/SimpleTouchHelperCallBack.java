package com.example.geeknews.widget;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.geeknews.ui.gold.adapter.RlvGoldAdapter;

/**
 * Created by 张嘉河 on 2019/4/19.
 */

public class SimpleTouchHelperCallBack extends ItemTouchHelper.Callback {
    private TouchCallBack mTouchCallBack;
    private boolean mSwipeEnable;

    public  SimpleTouchHelperCallBack(TouchCallBack touchCallBack) {
        mTouchCallBack = touchCallBack;
    }

    /**
     * 返回可以滑动的方向，一般使用makeMovementFlags(int,int)或makeFlag(int,int)
     * 来构造我们的返回值
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //允许上下拖拽
        int drag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //允许想左滑动
        int swipe = ItemTouchHelper.LEFT;
        return makeMovementFlags(drag,swipe);
    }

    /**
     * 上下拖动
     * tem时回调，可以调用Adapter的notifyItemMoved方法交换
     * 两个ViewHolder的位置，最后返回true，标识被拖动的ViewHolder已经
     * 移动到了目的位置
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mTouchCallBack.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mTouchCallBack.onItemDelete(viewHolder.getAdapterPosition());
    }

    /**
     * 支持长按拖动 默认true
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    /**
     * 支持滑动，既可以调用到onSwiped()方法，默认是true
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return mSwipeEnable;
    }

    /**
     * 设置是否支持滑动
     * @param enable
     */
    public void setSwipeEnable(boolean enable){
        mSwipeEnable = enable;
    }
}
