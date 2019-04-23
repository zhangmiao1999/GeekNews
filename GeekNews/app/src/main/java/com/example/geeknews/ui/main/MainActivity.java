package com.example.geeknews.ui.main;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseActivity;
import com.example.geeknews.base.BasePresenter;
import com.example.geeknews.constant.Constants;
import com.example.geeknews.presenter.MainPresenter;
import com.example.geeknews.ui.about.fragment.AboutFragment;
import com.example.geeknews.ui.collect.fragment.CollectFragment;
import com.example.geeknews.ui.gank.fragment.GankFragment;
import com.example.geeknews.ui.gold.fragment.GoldFragment;
import com.example.geeknews.ui.setting.fragment.SettingsFragment;
import com.example.geeknews.ui.vtex.fragment.V2exFragment;
import com.example.geeknews.ui.wechat.fragment.WechatFragment;
import com.example.geeknews.ui.zhihu.fragment.ZhihuDailyNewsFragment;
import com.example.geeknews.view.EmptyView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindView;

import static com.example.geeknews.constant.Constants.TYPE_GANK;
import static com.example.geeknews.constant.Constants.TYPE_WECHAT;

public class MainActivity extends BaseActivity<EmptyView,MainPresenter> {

    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.nv)
    NavigationView mNv;
    @BindView(R.id.dl)
    DrawerLayout mDl;
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.TabTitle)
    TextView mTabTitle;
    private ArrayList<Fragment> mFragments;
    private ArrayList<Integer> mTitles;
    private FragmentManager mManager;
    private int mLastPosition;
    @BindView(R.id.toolbar_container)
    FrameLayout mtoolbar_container;
    @BindView(R.id.search_view)
    MaterialSearchView mMaterialSearchView;
    private MenuItem mSearchItem;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mManager = getSupportFragmentManager();
        mToolBar.setTitle("");
        mTabTitle.setTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(mToolBar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mToolBar, R.string.a, R.string.a);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorWhite));
        mDl.addDrawerListener(toggle);
        toggle.syncState();

        //初始化Fragment
        initFragment();
        //初始化标题
        initTitle();
        addZhihuDailyFragment();

        mMaterialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //按下搜索或者提交的时候回调,
                //ToastUtil.showShort("提交的内容:"+query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //搜索框内容发生改变的回调,
                //ToastUtil.showShort(newText);
                return false;
            }
        });

        mMaterialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //搜索框展示
                //ToastUtil.showShort("展示");
            }

            @Override
            public void onSearchViewClosed() {
                //搜索框隐藏
                //ToastUtil.showShort("隐藏");
            }
        });
    }

    private void addZhihuDailyFragment() {
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.add(R.id.fragment_container, mFragments.get(0));
        transaction.commit();
        mTabTitle.setText(R.string.zhihu);
    }

    private void initTitle() {
        mTitles = new ArrayList<>();
        mTitles.add(R.id.zhihu);
        mTitles.add(R.id.wechat);
        mTitles.add(R.id.gank);
        mTitles.add(R.id.gold);
        mTitles.add(R.id.v2ex);
        mTitles.add(R.id.collect);
        mTitles.add(R.id.settings);
        mTitles.add(R.id.about);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new ZhihuDailyNewsFragment());
        mFragments.add(new WechatFragment());
        mFragments.add(new GankFragment());
        mFragments.add(new GoldFragment());
        mFragments.add(new V2exFragment());
        mFragments.add(new CollectFragment());
        mFragments.add(new SettingsFragment());
        mFragments.add(new AboutFragment());
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initListener() {
        mNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId != R.id.optionsTitle && itemId != R.id.intoTitle) {
                    item.setChecked(true);
                    switch (itemId) {
                        case R.id.zhihu:
                            switchFragment(Constants.TYPE_ZHIHU);
                            mTabTitle.setText(R.string.zhihu);
                            break;
                        case R.id.wechat:
                            switchFragment(TYPE_WECHAT);
                            mTabTitle.setText(R.string.wechat);
                            break;
                        case R.id.gank:
                            switchFragment(Constants.TYPE_GANK);
                            mTabTitle.setText(R.string.gank);
                            break;
                        case R.id.gold:
                            switchFragment(Constants.TYPE_GOLD);
                            mTabTitle.setText(R.string.gold);
                            break;
                        case R.id.v2ex:
                            switchFragment(Constants.TYPE_V2EX);
                            mTabTitle.setText(R.string.v2ex);
                            break;
                        case R.id.collect:
                            switchFragment(Constants.TYPE_COLLECT);
                            mTabTitle.setText(R.string.collect);
                            break;
                        case R.id.settings:
                            switchFragment(Constants.TYPE_SETTINGS);
                            mTabTitle.setText(R.string.settings);
                            break;
                        case R.id.about:
                            switchFragment(Constants.TYPE_ABOUT);
                            mTabTitle.setText(R.string.about);
                            break;
                    }
                    //点击之后关闭侧滑
                    mDl.closeDrawer(Gravity.LEFT);
                } else {
                    item.setChecked(false);
                }

                return false;
            }
        });
    }

    private void switchFragment(int type) {
        FragmentTransaction transaction = mManager.beginTransaction();
        Fragment fragment = mFragments.get(type);
        Fragment lastFragment = mFragments.get(mLastPosition);
        if (!fragment.isAdded()) {
            transaction.add(R.id.fragment_container, fragment);
        }
        transaction.hide(lastFragment);
        transaction.show(fragment);
        transaction.commit();
        //保存上一个点击的下标
        mLastPosition = type;

        //显示隐藏搜索框
        if (type == TYPE_WECHAT || type == TYPE_GANK) {
            mSearchItem.setVisible(true);
        } else {
            mSearchItem.setVisible(false);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        mSearchItem = menu.findItem(R.id.action_search);
        //隐藏搜索框
        mSearchItem.setVisible(false);
        mMaterialSearchView.setMenuItem(mSearchItem);
        return true;
    }

    /**
     * 回退键的回调
     */
    @Override
    public void onBackPressed() {
        if (mMaterialSearchView.isSearchOpen()) {
            mMaterialSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }

        new AlertDialog.Builder(this)
                .setTitle("提交")
                .setMessage("确定退出GeekNesw吗")
                .setPositiveButton("取消",null)
                .setNegativeButton("确定",null)
                .show();
    }
    //张淼

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("确定退出GeekNews吗")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
        }
        return false;
    }

}
