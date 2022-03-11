package com.sky.uidesign.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.sky.uidesign.api.IMVPView;
import com.sky.uidesign.widget.DialogLoading;

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/10 6:43 下午
 * @Version: 1.0
 */
public abstract class BaseFragment extends Fragment implements IMVPView {
    //    onAttach() -> onCreate -> onCreateView -> onViewCreate -> onActivityCreated -> onStart -> onResume -> onPause -> onStop -> onDestoryView -> onDestory -> onDetach
    protected AppCompatActivity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        return view != null ? view : super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract int getLayoutId();

    @Override
    public void setCenterTitle(@NonNull String title) {
    }

    @Override
    public void setRightTitle(@NonNull String title) {

    }

    @Override
    public void showToast(int resId) {
        Toast.makeText(activity, resId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToast(@NonNull String text) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show();
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
