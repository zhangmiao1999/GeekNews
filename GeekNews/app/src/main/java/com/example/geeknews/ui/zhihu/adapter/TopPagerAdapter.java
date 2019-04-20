package com.example.geeknews.ui.zhihu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.model.bean.NewsListBean;

import java.util.ArrayList;

/**
 * Created by 张嘉河 on 2019/4/10.
 */

public class TopPagerAdapter extends PagerAdapter {

    private final ArrayList<NewsListBean.TopStoriesBean> mList;
    private final DailyAdapter.OnPagerItemClickListener mOnPagerItemClickListener;
    private Context mContext;

    public TopPagerAdapter(ArrayList<NewsListBean.TopStoriesBean> list, DailyAdapter.OnPagerItemClickListener onPagerItemClickListener) {

        mList = list;
        mOnPagerItemClickListener = onPagerItemClickListener;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        mContext = container.getContext();
        //加载布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.top_viewpager, null);
        ImageView vp_img = view.findViewById(R.id.vp_img);
        TextView vp_title = view.findViewById(R.id.vp_title);
        Glide.with(mContext).load(mList.get(position).getImage()).into(vp_img);
        vp_title.setText(mList.get(position).getTitle());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
