package com.example.geeknews.ui.zhihu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.geeknews.R;
import com.example.geeknews.base.BaseApplication;
import com.example.geeknews.model.bean.NewsListBean;
import com.example.geeknews.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 张嘉河 on 2019/4/10.
 */

public class DailyAdapter extends RecyclerView.Adapter {
    public   ArrayList<NewsListBean.TopStoriesBean> mVpList;
    public final ArrayList<NewsListBean.StoriesBean> mNewsList;
    private final Context mContext;
    private String mDate = BaseApplication.getInstance().getString(R.string.todayNews);
    ;
    private final int ITEM_VP = 0;
    private final int ITEM_DATE = 1;
    private final int ITEM_CONTENT = 2;
    private OnItemClickListener mOnItemClickListener;
    private OnPagerItemClickListener mOnPagerItemClickListener;
    private int mVpCurrentPosition;
    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {

        }
    };

    public DailyAdapter(ArrayList<NewsListBean.TopStoriesBean> vpList,ArrayList<NewsListBean.StoriesBean> newsList, Context context) {
        mVpList = vpList;
        mNewsList = newsList;
        mContext = context;
    }



    public void addData(NewsListBean bean) {
        mVpList.clear();
        if (bean.getTop_stories() != null && bean.getTop_stories().size() > 0) {
            mVpList.addAll(bean.getTop_stories());
        }
        mNewsList.clear();
        if (bean.getStories() != null && bean.getStories().size() > 0) {
            mNewsList.addAll(bean.getStories());
        }
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public interface OnPagerItemClickListener {
        void onPagerItemClick(int position);
    }

    public void setOnPagerItemClickListener(OnPagerItemClickListener listener) {
        mOnPagerItemClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_VP) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_top, null);
            return new TopViewHolder(inflate);
        } else if (viewType == ITEM_DATE) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_date, null);
            return new DateViewHolder(inflate);
        } else {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_content, null);
            return new ContentViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == ITEM_VP) {
            final TopViewHolder th = (TopViewHolder) holder;
            TopPagerAdapter topPagerAdapter = new TopPagerAdapter(mVpList, new OnPagerItemClickListener() {
                @Override
                public void onPagerItemClick(int position) {
                    if (mOnPagerItemClickListener != null) {
                        mOnPagerItemClickListener.onPagerItemClick(position);
                    }
                }
            });
            th.mVpAadapter.setAdapter(topPagerAdapter);
            th.mVpAadapter.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    mVpCurrentPosition = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            if (mVpList.size() > 0) {
                mTimerTask = new TimerTask() {
                    @Override
                    public void run() {
                        MainActivity ma = (MainActivity) mContext;
                        ma.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                th.mVpAadapter.setCurrentItem(mVpCurrentPosition);
                                if (mVpCurrentPosition == mVpList.size() - 1) {
                                    mVpCurrentPosition = -1;
                                }
                                mVpCurrentPosition++;
                            }
                        });
                    }
                };
                new Timer().schedule(mTimerTask, 0, 5000);
            }
        } else if (itemViewType == ITEM_DATE) {
            DateViewHolder dateViewHolder = (DateViewHolder) holder;
            dateViewHolder.mTodaynews.setText(mDate);
        } else {
            ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
            int realPosition = position - 1;
            if (mVpList.size() > 0) {
                realPosition -= 1;
            }
            NewsListBean.StoriesBean bean = mNewsList.get(realPosition);
            contentViewHolder.mTitle.setText(bean.getTitle());
            if (bean.getImages() != null && bean.getImages().size() > 0) {
                RequestOptions options = new RequestOptions().placeholder(R.mipmap.ic_launcher);
                Glide.with(mContext).load(bean.getImages().get(0)).apply(options).into(contentViewHolder.mImg);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mVpList.size() > 0) {
            return mNewsList.size() + 2;
        } else {
            return mNewsList.size() + 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mVpList.size() > 0) {
            if (position == 0) {
                return ITEM_VP;
            } else if (position == 1) {
                return ITEM_DATE;
            } else {
                return ITEM_CONTENT;
            }
        } else {
            if (position == 0) {
                return ITEM_DATE;
            } else {
                return ITEM_CONTENT;
            }
        }
    }

    class TopViewHolder extends RecyclerView.ViewHolder {

        private final ViewPager mVpAadapter;

        public TopViewHolder(View itemView) {
            super(itemView);
            mVpAadapter = itemView.findViewById(R.id.vp);
        }
    }

    class DateViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTodaynews;

        public DateViewHolder(View itemView) {
            super(itemView);
            mTodaynews = itemView.findViewById(R.id.todaynews);
        }
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImg;
        private final TextView mTitle;

        public ContentViewHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.img);
            mTitle = itemView.findViewById(R.id.title);
        }
    }
}
