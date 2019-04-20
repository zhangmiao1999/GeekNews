package com.example.geeknews.constant;

import android.os.Environment;

import com.example.geeknews.base.BaseApplication;

import java.io.File;

/**
 * Created by asus on 2019/3/5.
 */

public interface Constants {
    int TYPE_ZHIHU = 0;
    int TYPE_WECHAT = 1;
    int TYPE_GANK = 2;
    int TYPE_GOLD = 3;
    int TYPE_V2EX = 4;
    int TYPE_COLLECT = 5;
    int TYPE_SETTINGS = 6;
    int TYPE_ABOUT = 7;


    boolean isDebug = true;


    String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator + "codeest" + File.separator + "GeekNews";

    String FILE_PROVIDER_AUTHORITY="com.baidu.geek.fileprovider";

    //网络缓存的地址
    String PATH_DATA = BaseApplication.getInstance().getCacheDir().getAbsolutePath() +
            File.separator + "data";

    String PATH_CACHE = PATH_DATA + "/NetCache";
    String DATA = "data";
    //夜间模式
    String MODE = "mode";
    String NIGHT_CURRENT_FRAG_POS = "fragment_pos";
}
