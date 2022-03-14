package com.sky.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.sky.common.utils.SPUtils;
import com.sky.common.utils.ToastUtils;
import com.sky.ui.api.IView;
import com.sky.ui.viewmodel.BaseVM;
import com.sky.ui.widget.DialogLoading;

/**
 * Created by libin on 2020/05/09 3:14 PM Saturday.
 */
public abstract class MVVMFragment<V extends ViewBinding, VM extends BaseVM> extends Fragment implements IView {
    public V binding;
    public VM viewModel;

    protected abstract V getBinding(LayoutInflater inflater, ViewGroup container);

    public abstract VM getViewModel();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParameters();
    }

    public void initParameters() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = getBinding(inflater, container);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = getViewModel();
    }

    @Override
    public void setCenterTitle(TextView tv, @NonNull String title) {

    }

    @Override
    public void showToast(int resId) {
        ToastUtils.showLong(getContext(), resId);
    }

    @Override
    public void showToast(@NonNull String text) {
        ToastUtils.showLong(getContext(), text);
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
        DialogLoading.showDialog(getContext());
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

    @Override
    public void onDetach() {
        super.onDetach();
        viewModel = null;
    }
}
