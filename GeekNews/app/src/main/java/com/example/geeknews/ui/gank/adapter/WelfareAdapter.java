package com.example.geeknews.ui.gank.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.geeknews.R;
import com.example.geeknews.model.bean.WelfareListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张嘉河 on 2019/4/17.
 */

public class WelfareAdapter extends RecyclerView.Adapter {
    public ArrayList<WelfareListBean.ResultsBean> mBeans;
    private Context mContext;

    public WelfareAdapter(ArrayList<WelfareListBean.ResultsBean> beans) {

        mBeans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.welfare_item, null);
        return new WelfareViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        WelfareListBean.ResultsBean resultsBean = mBeans.get(position);
        String url = resultsBean.getUrl();
        WelfareViewHolder welfareViewHolder = (WelfareViewHolder) holder;

        Glide.with(mContext).load(url).into(welfareViewHolder.mWelfare_img);

    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public void setData(List<WelfareListBean.ResultsBean> results) {
        mBeans.addAll(results);
        notifyDataSetChanged();
    }


    class WelfareViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mWelfare_img;

        public WelfareViewHolder(View itemView) {
            super(itemView);
            mWelfare_img = itemView.findViewById(R.id.welfare_img);

        }
    }

}
