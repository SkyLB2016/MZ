package com.sky.uidesign.api;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

/**
 * Created by libin on 2020/05/09 2:04 PM Saturday.
 */
public interface IMVVMView {
    void showToast(@StringRes int resId);

    void showToast(@NonNull String text);

    void showLoading();

    void disLoading();

    void showContent();

    void onRefreshEmpty();

    void onRefreshFailure(String msg);
}
