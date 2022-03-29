package com.sky.ui.activity;

import com.sky.ui.viewmodel.BaseVM;

/**
 * Created by SKY on 2017/8/22.
 */
public abstract class RefreshVM extends BaseVM {
    protected int page = 1;
    protected int totalCount = 0;

    public void getData() {
        page = 1;
    }

    public void onRefresh() {
        page = 1;
    }

}
