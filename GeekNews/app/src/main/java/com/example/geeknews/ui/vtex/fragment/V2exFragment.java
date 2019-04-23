package com.example.geeknews.ui.vtex.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.base.BaseView;
import com.example.geeknews.model.bean.V2exListBean;
import com.example.geeknews.model.bean.V2exTabBean;
import com.example.geeknews.presenter.vtex.V2exPresenter;
import com.example.geeknews.ui.vtex.activity.NodenavigationActivity;
import com.example.geeknews.ui.vtex.adapter.V2exVpAdapter;
import com.example.geeknews.view.V2exV;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 张嘉河 on 2019/4/3.
 */

public class V2exFragment extends BaseFragment<V2exV, V2exPresenter> implements BaseView {

    private static final String TAG = "V2exFragment";
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.gold_img)
    ImageView mGoldImg;
    @BindView(R.id.vp)
    ViewPager mVp;
    private String mUrl = "https://www.v2ex.com/";
    private ArrayList<V2exTabBean> mList = new ArrayList();

    @Override
    protected V2exPresenter initPresenter() {
        return new V2exPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold;
    }

    @Override
    protected void initView() {

    }
    @OnClick(R.id.gold_img)
    public void onClick(){
        startActivity(new Intent(getContext(), NodenavigationActivity.class));
    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(mUrl).get();
                    //查找id是Tabs的div元素，
                    Element tabs = doc.select("div#Tabs").first();
                    //查找带有herf属性的a元素
                    Elements allTabs = tabs.select("a[href]");
                    for (Element allTab : allTabs) {
                        String linkHref = allTab.attr("href");
                        String tab = allTab.text();
                        mList.add(new V2exTabBean(linkHref, tab));
//                        Log.d(TAG, "linkHref: " + linkHref + ",tab：" + tab);
                    }
//                    Log.d(TAG, "mList: " + mList.toString());
                    final ArrayList<BaseFragment> fragments = new ArrayList<>();
                    for (int i = 0; i < mList.size(); i++) {
                        V2exChildFragment childFragment = new V2exChildFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("linkHref", mList.get(i).linkHref);
                        childFragment.setArguments(bundle);
                        fragments.add(childFragment);
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            V2exVpAdapter adapter = new V2exVpAdapter(getChildFragmentManager(), mList, fragments);
                            mVp.setAdapter(adapter);
                            mTabLayout.setupWithViewPager(mVp);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
