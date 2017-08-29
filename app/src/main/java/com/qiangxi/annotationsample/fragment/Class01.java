package com.qiangxi.annotationsample.fragment;
/*
 * Copyright @2017 甘肃诚诚网络技术有限公司 All rights reserved.
 * 甘肃诚诚网络技术有限公司 专有/保密源代码,未经许可禁止任何人通过任何
 * 渠道使用、修改源代码.
 * 日期 2017/8/29 11:01
 */

import android.support.annotation.NonNull;

import com.qiangxi.BindClass;

/**
 * @company: 甘肃诚诚网络技术有限公司
 * @project: AnnotationSample
 * @package: com.qiangxi.annotationsample.activity
 * @version: V1.0
 * @author: 任强强
 * @date: 2017/8/29 11:01
 * @description: <p>
 * <p>
 * </p>
 */

@BindClass()
public abstract class Class01 implements Comparable, CharSequence {
    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }

    @BindClass(700)
    private class class007 {

    }
}
