package com.example.geeknews.ui.zhihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseActivity;
import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.presenter.zhihu.CalendarPresenter;
import com.example.geeknews.util.DateUtil;
import com.example.geeknews.view.EmptyView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends BaseActivity<EmptyView,CalendarPresenter> implements View.OnClickListener {

    private static final String TAG = "CalendarActivity";
    private Toolbar mToolBar;
    private MaterialCalendarView mViewCalender;
    private TextView mTvCalenderEnter;
    private CalendarDay mDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initView();
    }

    @Override
    protected CalendarPresenter initPresenter() {
        return new CalendarPresenter();
    }

    @Override
    protected void initView() {
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);

        mToolBar.setNavigationIcon(R.mipmap.back);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mViewCalender = (MaterialCalendarView) findViewById(R.id.view_calender);
        mTvCalenderEnter = (TextView) findViewById(R.id.tv_calender_enter);

        /*mViewCalender.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(2013, 5, 20))
                .setMaximumDate(CalendarDay.from(DateUtil.getCurrentYear(), DateUtil.getCurrentMonth(), DateUtil.getCurrentDay()))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();*/
        mViewCalender.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mDate = date;
            }
        });
        mTvCalenderEnter.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_calendar;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_calender_enter:
                sendCalendar();
                break;
        }
    }
    String time = "";
    private void sendCalendar() {

        if (mDate != null) {
            Date date = mDate.getDate();
            SimpleDateFormat format = new SimpleDateFormat(DateUtil.DEFAULT_DATE_FORMAT);
            String format1 = format.format(date);

            Log.d(TAG, "format: "+format1);

            char[] chars = format1.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (Character.isDigit(chars[i])){
                    time += chars[i];
                }
            }
            Log.d(TAG, "time: "+time);

            EventBus.getDefault().post(time);
        } else {
            Toast.makeText(this, "请选择时间", Toast.LENGTH_LONG).show();
        }
        finish();
    }
}
