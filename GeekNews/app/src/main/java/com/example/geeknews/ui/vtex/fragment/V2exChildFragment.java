package com.example.geeknews.ui.vtex.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.model.bean.V2exListBean;
import com.example.geeknews.presenter.vtex.V2exChildPresenter;
import com.example.geeknews.ui.vtex.adapter.V2exRlvAdapter;
import com.example.geeknews.view.AllView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 张嘉河 on 2019/4/19.
 */

public class V2exChildFragment extends BaseFragment<AllView, V2exChildPresenter> {

    private static final String TAG = "V2exChildFragment";
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    private String mLinkHref;
    private String mUrl = "https://www.v2ex.com";
    private ArrayList<V2exListBean> mList = new ArrayList<>();


    @Override
    protected V2exChildPresenter initPresenter() {
        return new V2exChildPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2ex_child;
    }

    @Override
    protected void initView() {
        mSmart.setRefreshHeader(new MaterialHeader(getContext()));
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        mLinkHref = arguments.getString("linkHref");
        Log.d(TAG, "initData: " + mLinkHref);

        new Thread(new Runnable() {

            private String mEnd;
            private String mTime;
            private String mReply;
            public String src;
            private String mComment;
            private String mTitlt;
            private String mNode;
            private String mAuthor;

            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(mUrl + mLinkHref).get();

                    if (document != null) {
                        //图片
                        Elements select = document.select("div.item.cell");
                        for (Element item : select) {
                            Elements images = item.select("table tr td img.avatar");
                            if (images.size() > 0) {
                                src = images.get(0).attr("src");
//                                Log.d(TAG, "src: " + src);
                            }
                            //评论数量
                            Elements select1 = item.select("table  tbody tr td a.count_livid");
                            if (select1.size() > 0) {
                                mComment = select1.get(0).text();
//                                Log.e(TAG, "livid: " + mComment);
                            }
                            Elements select2 = item.select("table tbody tr td span.item_title>a");
                            if (select2.size() > 0) {
                                mTitlt = select2.get(0).text();
//                                Log.d(TAG, "标题: " + mTitlt);
                            }
                            Elements select3 = item.select("table tbody tr td a.node");
                            if (select3.size() > 0) {
                                mNode = select3.get(0).text();
//                                Log.d(TAG, "node: " + mNode);
                            }
                            Elements select4 = item.select("table  tr td span.topic_info>strong>a");
                            if (select4.size() > 0) {
                                mAuthor = select4.get(0).text();
//                                Log.d(TAG, "author: " + mAuthor);
                            }
                            //最后评论人
                            if (select4.size() > 1) {
                                String end = select4.get(1).text();
//                                Log.d(TAG, "回复: " + end);
                            }
                            Elements select5 = item.select("table tbody tr td span.topic_info");
                            if (select5.size() > 0) {
                                mReply = select5.get(0).text();
                                String[] split = mReply.split(" • ");
                                if (split.length > 3) {
                                    mTime = split[2];
                                    mEnd = split[3];
                                }
//                                Log.d(TAG, "评论: " + mReply);
                            }
                            V2exListBean bean = new V2exListBean(mTitlt, src, mAuthor, mComment, mTime, mEnd, mNode);
                            mList.add(bean);
//                            Log.d(TAG, "哈哈哈: " + mList.toString());
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    V2exRlvAdapter adapter = new V2exRlvAdapter(mList);
                                    mRlv.setAdapter(adapter);
                                }
                            });
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
