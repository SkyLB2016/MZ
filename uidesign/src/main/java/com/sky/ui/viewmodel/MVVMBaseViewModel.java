package com.sky.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.sky.ui.api.IMVVMViewModel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by libin on 2020/05/09 1:11 PM Saturday.
 */
public class MVVMBaseViewModel<V> extends ViewModel implements IMVVMViewModel<V> {
    private Reference<V> reference;

    public void attachUI(V view) {
        reference = new WeakReference<>(view);
    }


    @Override
    public V getView() {
        if (reference == null) return null;
        return reference.get();
    }

    @Override
    public boolean isUIAttached() {
        return reference != null & reference.get() != null;
    }

    @Override
    public void detachUI() {
        if (reference != null) {
            reference.clear();
            reference = null;
        }
    }
}
