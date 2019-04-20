package com.example.geeknews.ui.zhihu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.base.BaseApplication;
import com.example.geeknews.model.callback.OnItemClick;
import com.example.geeknews.model.bean.HotListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张嘉河 on 2019/4/11.
 */

public class HotAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private final ArrayList<HotListBean.RecentBean> mBeans;

    public HotAdapter(Context context, ArrayList<HotListBean.RecentBean> beans) {
        mContext = context;
        mBeans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HotViewHolder(LayoutInflater.from(BaseApplication.getInstance()).inflate(R.layout.item_content, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        HotViewHolder hh = (HotViewHolder) holder;
        hh.mTitle.setText(mBeans.get(position).getTitle());
        Glide.with(mContext).load(mBeans.get(position).getThumbnail()).into(hh.mImg);

        hh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mResultCallBack!=null){
                    mResultCallBack.OnItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public void addData(List<HotListBean.RecentBean> recent) {
        mBeans.addAll(recent);
        notifyDataSetChanged();
    }

    class HotViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImg;
        private final TextView mTitle;

        public HotViewHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.img);
            mTitle = itemView.findViewById(R.id.title);
        }
    }

    public OnItemClick mResultCallBack;

    public void setResultCallBack(OnItemClick resultCallBack) {
        mResultCallBack = resultCallBack;
    }
}
