package com.sky.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.sky.common.utils.ToastUtils;
import com.sky.ui.api.IView;
import com.sky.ui.widget.DialogLoading;

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/10 6:43 下午
 * @Version: 1.0
 */
public abstract class BaseFragment<V extends ViewBinding> extends Fragment implements IView {
    //    onAttach() -> onCreate -> onCreateView -> onViewCreate -> onActivityCreated -> onStart -> onResume -> onPause -> onStop -> onDestoryView -> onDestory -> onDetach
    protected AppCompatActivity activity;
    protected V binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(getLayoutId(), container, false);
//        return view != null ? view : super.onCreateView(inflater, container, savedInstanceState);
        binding = getBinding(inflater, container);
        return binding.getRoot();
    }

    protected abstract V getBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    @Override
    public void setCenterTitle(TextView tv, String title) {
    }

    @Override
    public void showToast(int resId) {
        ToastUtils.showLong(activity, resId);
    }

    @Override
    public void showToast(@NonNull String text) {
        ToastUtils.showLong(activity, text);
    }

    @Override
    public void showLoading() {
        DialogLoading.showDialog(activity);
    }

    @Override
    public void disLoading() {
        DialogLoading.disDialog();
    }
}
