package com.zzr.jetpacktest.entity;

import androidx.annotation.NonNull;

/**
 * @Author zzr
 * @Desc
 * @Date 2020/10/10
 */
public class TestBean implements Cloneable {
    private String name;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        TestBean testBean = null;
        try {
            testBean = (TestBean) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return testBean;
    }
}
