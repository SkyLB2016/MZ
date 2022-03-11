package com.sky.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.sky.ui.api.IMVVMView;
import com.sky.ui.viewmodel.MVVMBaseViewModel;
import com.sky.ui.widget.DialogLoading;

/**
 * Created by libin on 2020/05/09 3:14 PM Saturday.
 */
public abstract class MVVMFragment<V extends ViewBinding, VM extends MVVMBaseViewModel> extends Fragment implements IMVVMView {
    public V viewBinding;
    public VM viewModel;

    protected abstract V getViewBinding(LayoutInflater inflater, ViewGroup container);

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
        viewBinding = getViewBinding(inflater, container);
        return viewBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = getViewModel();
        if (viewModel != null) {
            viewModel.attachUI(this);
        }
    }
    @Override
    public void showToast(int resId) {
        Toast.makeText(getContext(), resId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToast(@NonNull String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        DialogLoading.showDialog(getContext());
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

    @Override
    public void onDetach() {
        super.onDetach();
        if (viewModel != null && viewModel.isUIAttached()) {
            viewModel.detachUI();
        }
    }
}
