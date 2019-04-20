package com.example.geeknews.ui.gold.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseActivity;
import com.example.geeknews.constant.Constants;
import com.example.geeknews.presenter.EmptyPresenter;
import com.example.geeknews.ui.gold.GoldShowBean;
import com.example.geeknews.ui.gold.adapter.RlvGoldAdapter;
import com.example.geeknews.view.EmptyView;
import com.example.geeknews.widget.SimpleTouchHelperCallBack;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {


    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private ArrayList<GoldShowBean> mList;
    private RlvGoldAdapter mAdapter;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    protected void initView() {
        mList = (ArrayList<GoldShowBean>) getIntent().getSerializableExtra(Constants.DATA);
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationIcon(R.mipmap.back);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAct();
            }
        });
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RlvGoldAdapter(mList);
        mRlv.setAdapter(mAdapter);
        mRlv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //拖拽移动和侧滑删除的功能
        SimpleTouchHelperCallBack simpleTouchHelperCallBack = new SimpleTouchHelperCallBack(mAdapter);
        simpleTouchHelperCallBack.setSwipeEnable(true);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleTouchHelperCallBack);
        itemTouchHelper.attachToRecyclerView(mRlv);
    }

    private void finishAct() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DATA, mList);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAct();
    }
}
