package com.sky.ui.api;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

/**
 * Created by libin on 2020/05/09 2:04 PM Saturday.
 */
public interface IView {
    void setCenterTitle(TextView tv,@NonNull String title);

    void showToast(@StringRes int resId);

    void showToast(@NonNull String text);

    void showLoading();

    void disLoading();
}
