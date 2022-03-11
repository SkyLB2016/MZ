package com.sky.uidesign.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.sky.uidesign.api.IMVPView;
import com.sky.uidesign.widget.DialogLoading;

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/10 3:30 下午
 * @Version: 1.0
 */
public abstract class BaseActivity extends AppCompatActivity implements IMVPView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    protected abstract int getLayoutId();

    @Override
    public void setCenterTitle(@NonNull String title) {

    }

    @Override
    public void setRightTitle(@NonNull String title) {

    }

    @Override
    public void showToast(@StringRes int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToast(@NonNull String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        DialogLoading.showDialog(this);
    }

    @Override
    public void disLoading() {
        DialogLoading.disDialog();
    }
}
