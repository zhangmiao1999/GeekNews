package com.example.geeknews.ui.gold.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.geeknews.R;
import com.example.geeknews.constant.Constants;
import com.example.geeknews.ui.gold.GoldShowBean;
import com.example.geeknews.widget.TouchCallBack;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 张嘉河 on 2019/4/19.
 */

public class RlvGoldAdapter extends RecyclerView.Adapter implements TouchCallBack{
    private ArrayList<GoldShowBean> mList;
    private Context mContext;

    public RlvGoldAdapter(ArrayList<GoldShowBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.show_item, null);
        return new ShowViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ShowViewHolder showViewHolder = (ShowViewHolder) holder;

        showViewHolder.mTextView.setText(mList.get(position).title);
        showViewHolder.mSc.setChecked(mList.get(position).isChecked);
        showViewHolder.mSc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mList.get(position).isChecked = isChecked;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mList,fromPosition,toPosition);

        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDelete(int position) {
        mList.remove(position);

        notifyItemRemoved(position);
    }

    class ShowViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sc)
        SwitchCompat mSc;
        @BindView(R.id.tv)
        TextView mTextView;
        public ShowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
