package com.sky.uidesign.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sky.uidesign.api.IMVPView;
import com.sky.uidesign.presenter.BasePresenter;
import com.sky.uidesign.widget.DialogLoading;

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/10 2:17 下午
 * @Version: 1.0
 */
public abstract class BasePActivity<P extends BasePresenter> extends AppCompatActivity implements IMVPView {
    protected P p;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (p == null) p = createP();
    }

    protected abstract P createP();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        p.recycle();
    }

    @Override
    public void setCenterTitle(@NonNull String title) {

    }

    @Override
    public void setRightTitle(@NonNull String title) {

    }

    @Override
    public void showToast(int resId) {
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
