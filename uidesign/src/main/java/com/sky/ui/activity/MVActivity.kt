package com.sky.ui.activity

import android.R
import androidx.viewbinding.ViewBinding
import com.sky.ui.viewmodel.BaseVM
import androidx.appcompat.app.AppCompatActivity
import com.sky.ui.api.IView
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import com.sky.common.utils.ToastUtils
import com.sky.common.utils.SPUtils
import com.sky.ui.widget.DialogLoading

/**
 * Created by libin on 2020/05/08 6:48 PM Friday.
 */
abstract class MVActivity<V : ViewBinding, VM : BaseVM> : AppCompatActivity(), IView {
    abstract var binding: V
    abstract var viewModel: VM

    //    protected abstract fun getViewBinging(): V
//    protected abstract fun getVModel(): VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding!!.root)
    }

    fun setToolbar(toolbar: Toolbar?, title: String?) {
        setSupportActionBar(toolbar)
        //        getSupportActionBar().setTitle(title);
        setTitle(title)
    }

    fun showNavigationIcon() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) //就这一个也起作用，需要与 onOptionsItemSelected 配合使用
        supportActionBar!!.setHomeButtonEnabled(true) //必须与搭配第一个使用，不用这个，也行，目前没发现他的作用
        //getSupportActionBar().setDisplayShowHomeEnabled(true);//没啥用
    }

    override fun setCenterTitle(tv: TextView, title: String) {}
    override fun showToast(@StringRes resId: Int) {
        ToastUtils.showLong(this, resId)
    }

    override fun showToast(text: String) {
        ToastUtils.showLong(this, text)
    }

    override fun <T> getObject(text: String, value: T): T {
        return SPUtils.getInstance()[text, value] as T
    }

    override fun <T> setObject(text: String, value: T) {
        SPUtils.getInstance().put(text, value)
    }

    override fun showLoading() {
        DialogLoading.showDialog(this)
    }

    override fun disLoading() {
        DialogLoading.disDialog()
    }

    //Activity 自带的 menu 监听事件，toolbar不设置监听，默认使用的也是这个。
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> onBackPressed()//finish()
        }
        return super.onOptionsItemSelected(item)
    }
}