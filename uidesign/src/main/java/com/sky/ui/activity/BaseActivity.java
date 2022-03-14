package com.sky.ui.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.sky.common.utils.SPUtils;
import com.sky.common.utils.ToastUtils;
import com.sky.ui.api.IMVPView;
import com.sky.ui.api.IView;
import com.sky.ui.widget.DialogLoading;

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/10 3:30 下午
 * @Version: 1.0
 */
public abstract class BaseActivity<V extends ViewBinding> extends AppCompatActivity implements IView {
    protected V binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getBinding();
        setContentView(binding.getRoot());
    }

    protected abstract V getBinding();


    public void showNavigationIcon(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//就这一个也起作用，需要与 onOptionsItemSelected 配合使用
        getSupportActionBar().setHomeButtonEnabled(true);//必须与搭配第一个使用，不用这个，也行，目前没发现他的作用
        //getSupportActionBar().setDisplayShowHomeEnabled(true);//没啥用
    }

    @Override
    public void setCenterTitle(TextView tv, @NonNull String title) {

    }

    @Override
    public void showToast(@StringRes int resId) {
        ToastUtils.showLong(this, resId);
    }

    @Override
    public void showToast(@NonNull String text) {
        ToastUtils.showLong(this, text);
    }

    @Override
    public <T> T getObject(String text, T value) {
        return (T) SPUtils.getInstance().get(text, value);
    }

    @Override
    public <T> void setObject(String text, T value) {
        SPUtils.getInstance().put(text, value);
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
