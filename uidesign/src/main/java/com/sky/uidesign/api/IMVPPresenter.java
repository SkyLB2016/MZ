package com.sky.uidesign.api;

import android.os.Bundle;

import androidx.annotation.StringRes;

/**
 * @Description: MVP的P基类
 * @Author: 李彬
 * @CreateDate: 2021/8/10 2:19 下午
 * @Version: 1.0
 */
public interface IMVPPresenter {
    void getData();

    /**
     * sp缓存数据
     *
     * @return
     */
    void setObject(String text, Object value);

    /**
     * @param <T>
     * @return 获取sp缓存的数据
     */
    <T> T getObject(String text, T value);

//    /**
//     * 获取字符串
//     * @param resId
//     * @return
//     */
//    String getString(@StringRes int resId);

    /**
     * 销毁时释放资源
     */
    void recycle();
}
