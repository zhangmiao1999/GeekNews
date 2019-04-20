package com.example.geeknews.model.bean;

/**
 * Created by 张嘉河 on 2019/4/19.
 */

public class V2exTabBean {
    public String linkHref;
    public String tab;

    public V2exTabBean(String linkHref, String tab) {
        this.linkHref = linkHref;
        this.tab = tab;
    }

    @Override
    public String toString() {
        return "V2exTabBean{" +
                "linkHref='" + linkHref + '\'' +
                ", tab='" + tab + '\'' +
                '}';
    }
}
