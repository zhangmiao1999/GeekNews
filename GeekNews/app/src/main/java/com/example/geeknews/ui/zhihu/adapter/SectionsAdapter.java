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
import com.example.geeknews.model.bean.SectionsListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 张嘉河 on 2019/4/11.
 */

public class SectionsAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private final ArrayList<SectionsListBean.DataBean> mBeans;

    public SectionsAdapter(Context context, ArrayList<SectionsListBean.DataBean> beans) {

        mContext = context;
        mBeans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.section_item, null);
        return new SectionsViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SectionsViewHolder sectionsViewHolder = (SectionsViewHolder) holder;
        sectionsViewHolder.mSectionsKind.setText(mBeans.get(position).getName());
        sectionsViewHolder.mSectionsDes.setText(mBeans.get(position).getDescription());
        Glide.with(mContext).load(mBeans.get(position).getThumbnail()).into(sectionsViewHolder.mImg);
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public void addData(List<SectionsListBean.DataBean> data) {
        mBeans.addAll(data);
        notifyDataSetChanged();
    }

    class SectionsViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.section_bg)
        ImageView mImg;
        @BindView(R.id.section_kind)
        TextView mSectionsKind;
        @BindView(R.id.section_dec)
        TextView mSectionsDes;

        public SectionsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
