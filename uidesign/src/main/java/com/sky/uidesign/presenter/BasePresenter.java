package com.sky.uidesign.presenter;

import android.content.Context;

import com.sky.common.SPUtils;
import com.sky.uidesign.api.IMVPPresenter;
import com.sky.uidesign.api.IMVPView;

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/10 3:32 下午
 * @Version: 1.0
 */
public class BasePresenter<V extends IMVPView> implements IMVPPresenter {
    protected Context context;
    protected V mView;


    public BasePresenter(Context context) {
        this.context = context;
    }

    public BasePresenter(Context context, V mView) {
        this.context = context;
        this.mView = mView;
    }

    @Override
    public void getData() {
    }

    @Override
    public void setObject(String text, Object value) {
        SPUtils.getInstance().get(text, value);
    }

    @Override
    public <T> T getObject(String text, T value) {
        return (T) SPUtils.getInstance().get(text, value);
    }

    @Override
    public void recycle() {

    }
}
