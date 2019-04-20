package com.example.geeknews.ui.gold.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.base.BaseView;
import com.example.geeknews.constant.Constants;
import com.example.geeknews.presenter.gold.GoldPresenter;
import com.example.geeknews.ui.gold.GoldShowBean;
import com.example.geeknews.ui.gold.activity.ShowActivity;
import com.example.geeknews.ui.gold.adapter.GoldVpAdapter;
import com.example.geeknews.view.GoldV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 张嘉河 on 2019/4/3.
 */

public class GoldFragment extends BaseFragment<GoldV, GoldPresenter> implements BaseView {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.gold_img)
    ImageView mGoldImg;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<GoldShowBean> mList;
    private ArrayList<Fragment> mFragments;

    @Override
    protected GoldPresenter initPresenter() {
        return new GoldPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold;
    }

    @Override
    protected void initView() {
        initTitle();
        setFragments();
    }

    private void setFragments() {
        initFragment();
        GoldVpAdapter goldVpAdapter = new GoldVpAdapter(getChildFragmentManager(), mList,mFragments);

        mVp.setAdapter(goldVpAdapter);
        mTabLayout.setupWithViewPager(mVp);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            GoldShowBean goldShowBean = mList.get(i);
            if (goldShowBean.isChecked){
                mFragments.add(GoldDetailFragment.newInstance(goldShowBean.title));
            }
        }
    }

    private void initTitle() {
        mList = new ArrayList<>();
        mList.add(new GoldShowBean("工具资源",true));
        mList.add(new GoldShowBean("Android",true));
        mList.add(new GoldShowBean("iOS",true));
        mList.add(new GoldShowBean("设计",true));
        mList.add(new GoldShowBean("产品",true));
        mList.add(new GoldShowBean("阅读",true));
        mList.add(new GoldShowBean("前端",true));
        mList.add(new GoldShowBean("后端",true));
    }


    /**
     * 点击事件
     */
    @OnClick({R.id.gold_img})
    public void onClick(){
        go2Activity();
    }

    private void go2Activity() {
        Intent intent = new Intent(getContext(), ShowActivity.class);
        intent.putExtra(Constants.DATA, mList);
        startActivityForResult(intent,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            if (requestCode == 100 && resultCode == Activity.RESULT_OK){
                mList = (ArrayList<GoldShowBean>) data.getSerializableExtra(Constants.DATA);
                //刷新页面
                setFragments();
            }
        }
    }
}
