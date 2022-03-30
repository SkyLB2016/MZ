package com.sky.oa.activity

import android.Manifest
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.sky.common.utils.AppUtils
import com.sky.common.utils.LogUtils
import com.sky.oa.R
import com.sky.oa.databinding.ActivityMainBinding
import com.sky.oa.vm.MainVM
import com.sky.ui.activity.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.appBar.toolbar)
        title = ""//绑定toolBar后，把左侧的标题置为空
//        binding.appBar.toolbar.title = "标题"//setSupportActionBar 后此方法失效。
//        binding.appBar.tvCenter.setText("标题测试");
//        binding.appBar.toolbar.setSubtitle("副标题");
//        binding.appBar.toolbar.setNavigationOnClickListener();
//        binding.appBar.toolbar.setOnMenuItemClickListener();

        //显示返回键
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)//只用这一个也行
//        supportActionBar?.setHomeButtonEnabled(true)//必须与搭配第一个使用，不用这个也行，暂未发现他有什么用

//        binding.setActivity(this);//Databinding 的双向绑定
//        getLifecycle().addObserver(new MyObserver());


        //配置 NavigationBottomBar ；navigation 文件中的 fragment id 要与 menu文件中 item 的 id 相对应，否则无响应。
        //获取navigationFragment控制器
        val controller = findNavController(R.id.navFragment);
        //1、直接使用 bottomBar 设置,kotlin 模式独有
//        binding.bottomBar.setupWithNavController(controller)
        //2、使用 NavigationUI 配置 bottomBar 与 Fragment
        NavigationUI.setupWithNavController(binding.bottomBar, controller)


        //配置 ToolBar 自带的标题，configuration 配置的是 navigation 文件与menu文件共同的id，配置好后相当于同页面切换，没有返回键。
        val configuration = AppBarConfiguration.Builder(
            R.id.nav_home, R.id.nav_notes, R.id.nav_article, R.id.nav_my
        ).build();
//        AppBarConfiguration configuration = new AppBarConfiguration.Builder(R.id.nav_home).build();
        //1、使用 ToolBar 配置,kotlin 独有
//        binding.appBar.toolbar.setupWithNavController(controller, configuration)
        //2、通过 navigationUI 配置
//        NavigationUI.setupActionBarWithNavController(this, controller, configuration);
        //NavigationUI.setupWithNavController(binding.appBar.toolbar,controller,configuration)
        //不配置 configuration ，切换页面后，会显示返回键
//        NavigationUI.setupActionBarWithNavController(this, controller)
        //3、kotlin 衍生方法设置。
//        setupActionBarWithNavController(controller,configuration)
//        setupActionBarWithNavController(controller)

        //ToolBar 设置menu 监听后，会顶掉原有的 onOptionsItemSelected 监听
//        binding.appBar.toolbar.setOnMenuItemClickListener {}

//        binding.appBar.toolbar.title = binding.bottomBar.menu.getItem(0).title
        binding.appBar.tvCenter.text = binding.bottomBar.menu.getItem(0).title
        //bottomBar 设置监听后，navigation 引导会失效，需要重新建立引导
        binding.bottomBar.setOnItemSelectedListener { item ->
            binding.appBar.tvCenter.text = item.title//居中的标题
            NavigationUI.onNavDestinationSelected(item, controller)
            true
        }


        binding.fab.setOnClickListener {
            testMethod();
        }

        //content://com.android.fileexplorer.myprovider/external_files/AFile/ICP%E5%A4%87%E6%A1%88.txt
        //content://com.tencent.mtt.fileprovider/QQBrowser/Andro id/data/com.tencent.mtt/files/.ReaderTemp/thrdcall/contenturi/51cc4f665eaef803fe0fb39bf0a3bb38/%E5%B7%A5%E7%A8%8B%E9%80%9A%E6%93%8D%E4%BD%9C%E6%89%8B%E5%86%8C(%E6%89%8B%E6%9C%BA%E7%89%88).docx

        //mi8 wps content://cn.wps.moffice_eng.fileprovider/files/file/%E6%AC%A2%E8%BF%8E%E4%BD%BF%E7%94%A8WPS%20Office.docx
        //mi9 wps content://cn.wps.moffice_eng.fileprovider/external_storage_root/Android/data/cn.wps.moffice_eng/.Cloud/cn/21600292/f/3f40e3e8-274c-4809-823a-7700451f2bc5/0890086000102026345.20210907153748.51300189004570477510416311153689.docx
        //mi8 fil content://com.miui.securitycenter.zman.fileProvider/external/AFile/ICP%E5%A4%87%E6%A1%88.txt
        //mi9 fil content://com.android.fileexplorer.myprovider/external_files/AFile/ICP%E5%A4%87%E6%A1%88.txt

        //recycler.addOnItemTouchListener();
        AppUtils.isPermissions(
            this,
            arrayOf(
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            1119
        )

    }

    val vm = MainVM()

    private fun testMethod() {
//        vm.TestMethod(this)
        var a = 2
        var b = 3
        LogUtils.i("返回最大b3=="+a.coerceAtLeast(b))
        LogUtils.i("返回最小a2=="+a.coerceAtMost(b))
        LogUtils.i("返回2=="+ a.coerceAtMost(b))
        LogUtils.i("返回3=="+ a.coerceAtLeast(b))
    }

}
