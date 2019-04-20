package com.example.geeknews.ui.gold;

import java.io.Serializable;

/**
 * Created by 张嘉河 on 2019/4/19.
 */

public class GoldShowBean implements Serializable{
    public String title;
    public boolean isChecked;

    public GoldShowBean(String title, boolean isChecked) {
        this.title = title;
        this.isChecked = isChecked;
    }
}
