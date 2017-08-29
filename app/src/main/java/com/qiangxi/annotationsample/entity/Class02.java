package com.qiangxi.annotationsample.entity;
/*
 * Copyright @2017 甘肃诚诚网络技术有限公司 All rights reserved.
 * 甘肃诚诚网络技术有限公司 专有/保密源代码,未经许可禁止任何人通过任何
 * 渠道使用、修改源代码.
 * 日期 2017/8/29 11:02
 */

import com.qiangxi.BindMethod;

import java.util.List;
import java.util.Map;

/**
 * @company: 甘肃诚诚网络技术有限公司
 * @project: AnnotationSample
 * @package: com.qiangxi.annotationsample.entity
 * @version: V1.0
 * @author: 任强强
 * @date: 2017/8/29 11:02
 * @description: <p>
 * <p>
 * </p>
 */


public class Class02 {
    @BindMethod("注解method")
    private static final Map<String, String> method(String param, List<String> params) {

        return null;
    }
}
