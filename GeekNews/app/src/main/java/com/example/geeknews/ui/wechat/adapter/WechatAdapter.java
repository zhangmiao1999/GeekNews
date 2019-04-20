package com.example.geeknews.ui.wechat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.geeknews.R;
import com.example.geeknews.model.bean.WechatListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张嘉河 on 2019/4/17.
 */

public class WechatAdapter extends RecyclerView.Adapter {
    public ArrayList<WechatListBean.NewslistBean> mBeans;
    private Context mContext;

    public WechatAdapter(ArrayList<WechatListBean.NewslistBean> beans) {

        mBeans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View inflate = View.inflate(mContext, R.layout.wechat_item, null);
        return new WechatViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        WechatViewHolder wechatViewHolder = (WechatViewHolder) holder;
        WechatListBean.NewslistBean bean = mBeans.get(position);
        wechatViewHolder.mTitle.setText(bean.getTitle());
        wechatViewHolder.mTime.setText(bean.getCtime());
        wechatViewHolder.mDescription.setText(bean.getDescription());
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.ic_launcher);
        Glide.with(mContext).load(bean.getPicUrl()).apply(options).into(wechatViewHolder.mImage);
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public void setData(List<WechatListBean.NewslistBean> beans) {
        mBeans.addAll(beans);
        notifyDataSetChanged();
    }

    class WechatViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImage;
        private final TextView mTime;
        private final TextView mTitle;
        private final TextView mDescription;

        public WechatViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.wechat_img);
            mTime = itemView.findViewById(R.id.wechat_time);
            mTitle = itemView.findViewById(R.id.wechat_title);
            mDescription = itemView.findViewById(R.id.description);
        }
    }
}
