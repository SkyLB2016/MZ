package com.sky.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.CheckResult;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;

import com.sky.common.utils.SPUtils;
import com.sky.common.utils.ToastUtils;
import com.sky.ui.api.IView;
import com.sky.ui.viewmodel.BaseVM;
import com.sky.ui.widget.DialogLoading;

/**
 * Created by libin on 2020/05/08 6:48 PM Friday.
 */
public abstract class MVVMActivity<V extends ViewBinding, VM extends BaseVM> extends AppCompatActivity implements IView {
    public V binding;
    public VM viewModel;

    protected abstract V getBinding();

    protected abstract VM getViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getBinding();
        setContentView(binding.getRoot());
        viewModel = getViewModel();
    }

    public void setToolbar(Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle(title);
        setTitle(title);
    }

    public void showNavigationIcon() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//就这一个也起作用，需要与 onOptionsItemSelected 配合使用
        getSupportActionBar().setHomeButtonEnabled(true);//必须与搭配第一个使用，不用这个，也行，目前没发现他的作用
        //getSupportActionBar().setDisplayShowHomeEnabled(true);//没啥用
    }

    @Override
    public void setCenterTitle(TextView tv, String title) {
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

//    @Override
//    public void showContent() {
//    }
//
//    @Override
//    public void onRefreshEmpty() {
//    }
//
//    @Override
//    public void onRefreshFailure(String msg) {
//    }

    //Activity 自带的 menu 监听事件，toolbar不设置监听，默认使用的也是这个。
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
