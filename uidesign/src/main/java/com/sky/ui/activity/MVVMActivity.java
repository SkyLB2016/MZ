package com.sky.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.CheckResult;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;

import com.sky.ui.api.IMVVMView;
import com.sky.ui.viewmodel.MVVMBaseViewModel;
import com.sky.ui.widget.DialogLoading;

/**
 * Created by libin on 2020/05/08 6:48 PM Friday.
 */
public abstract class MVVMActivity<V extends ViewBinding, VM extends MVVMBaseViewModel> extends AppCompatActivity implements IMVVMView {
    public V viewDataBinding;
    public VM viewModel;

    @CheckResult
    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract int getBindingVariable();

    protected abstract VM getViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    public void setToolbar(String title, Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//就这一个也起作用，需要与 onOptionsItemSelected 配合使用
        getSupportActionBar().setHomeButtonEnabled(true);//必须与搭配第一个使用，不用这个，也行，目前没发现他的作用

//        getSupportActionBar().setDisplayShowHomeEnabled(true);//没啥用

//        getSupportActionBar().setTitle(title);
//        toolBarBinding.setAppTitle(new TitleEntity("学习笔记"));
        setTitle(title);
    }

    private void performDataBinding() {
//        viewDataBinding =
//        if (viewModel == null) {
//            viewModel = getViewModel();
//        }
//        if (viewModel != null) {
//            viewModel.attachUI(this);
//        }
//        if (getBindingVariable() > 0) {
//            viewDataBinding.setVariable(getBindingVariable(), viewModel);
//        }
//        viewDataBinding.executePendingBindings();
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

    @Override
    public void showContent() {
    }

    @Override
    public void onRefreshEmpty() {
    }

    @Override
    public void onRefreshFailure(String msg) {
    }

    //原来的
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
