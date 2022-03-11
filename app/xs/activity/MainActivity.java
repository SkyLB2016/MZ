package com.sky.xs.activity;

import static androidx.navigation.ActivityKt.findNavController;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleRegistry;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.sky.common.FileUtils;
import com.sky.common.LogUtils;
import com.sky.xs.R;
import com.sky.xs.databinding.ActivityMainBinding;
import com.sky.xs.entity.PoetryEntity;
import com.sky.xs.vm.MyObserver;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/10 5:44 下午
 * @Version: 1.0
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        @NonNull
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
//        ActivityMainBinding binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_main, null, false);
//        ActivityMainBinding binding =        DataBindingUtil.setContentView(this,R.layout.activity_main);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.actionBar.toolbar);
//        binding.setLifecycleOwner();
//        binding.actionBar.tvCenter.setText("标题测试");
//        binding.actionBar.toolbar.setTitle("标题");
//        binding.actionBar.toolbar.setSubtitle("副标题");
//        binding.actionBar.toolbar.setNavigationOnClickListener();
//        binding.actionBar.toolbar.setOnMenuItemClickListener();

        //显示返回键
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)//只用这一个也行
//        supportActionBar?.setHomeButtonEnabled(true)//必须与搭配第一个使用，不用这个也行，暂未发现他有什么用

        binding.setActivity(this);//Databinding 的双向绑定
//        getLifecycle().addObserver(new MyObserver());
//        new LifecycleRegistry(this);


        //配置 NavigationBottomBar ；navigation 文件中的 fragment id 要与 menu文件中 item 的 id 相对应，否则无响应。
        //获取navigationFragment控制器
        NavController controller = findNavController(this, R.id.navFragment);
        //1、直接使用 bottomBar 设置,kotlin 模式独有
//        binding.bottomBar.setupWithNavController(controller)
        //2、使用 NavigationUI 配置 bottomBar 与 Fragment
        NavigationUI.setupWithNavController(binding.bottomBar, controller);


        //配置 ToolBar 自带的标题，configuration 配置的是 navigation 文件与menu文件共同的id，配置好后相当于同页面切换，没有返回键。
        AppBarConfiguration configuration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_notes, R.id.nav_article, R.id.nav_my
        ).build();
//        AppBarConfiguration configuration = new AppBarConfiguration.Builder(R.id.nav_home).build();
        //1、使用 ToolBar 配置,kotlin 独有
//        binding.actionBar.toolbar.setupWithNavController(controller, configuration)
        //2、通过 navigationUI 配置
        NavigationUI.setupActionBarWithNavController(this, controller, configuration);
        //NavigationUI.setupWithNavController(binding.actionBar.toolbar,controller,configuration)
        //不配置 configuration ，切换页面后，会显示返回键
//        NavigationUI.setupActionBarWithNavController(this, controller)
        //3、kotlin 衍生方法设置。
//        setupActionBarWithNavController(controller,configuration)
//        setupActionBarWithNavController(controller)

        //ToolBar 设置menu 监听后，会顶掉原有的 onOptionsItemSelected 监听
//        binding.actionBar.toolbar.setOnMenuItemClickListener {}

//        findViewById(R.id.fab).setOnClickListener(view -> {
//            testMethod();
//        });


        //content://com.android.fileexplorer.myprovider/external_files/AFile/ICP%E5%A4%87%E6%A1%88.txt
        //content://com.tencent.mtt.fileprovider/QQBrowser/Andro id/data/com.tencent.mtt/files/.ReaderTemp/thrdcall/contenturi/51cc4f665eaef803fe0fb39bf0a3bb38/%E5%B7%A5%E7%A8%8B%E9%80%9A%E6%93%8D%E4%BD%9C%E6%89%8B%E5%86%8C(%E6%89%8B%E6%9C%BA%E7%89%88).docx

        //mi8 wps content://cn.wps.moffice_eng.fileprovider/files/file/%E6%AC%A2%E8%BF%8E%E4%BD%BF%E7%94%A8WPS%20Office.docx
        //mi9 wps content://cn.wps.moffice_eng.fileprovider/external_storage_root/Android/data/cn.wps.moffice_eng/.Cloud/cn/21600292/f/3f40e3e8-274c-4809-823a-7700451f2bc5/0890086000102026345.20210907153748.51300189004570477510416311153689.docx
        //mi8 fil content://com.miui.securitycenter.zman.fileProvider/external/AFile/ICP%E5%A4%87%E6%A1%88.txt
        //mi9 fil content://com.android.fileexplorer.myprovider/external_files/AFile/ICP%E5%A4%87%E6%A1%88.txt
    }

    public void onFabClick(View v) {
        LogUtils.i("onbackpress==");
        //        new InterAA().getA();

//        double a = 60 >> 3;
//        String aa = "dfdsf";
//        if (aa instanceof String) {
//            LogUtils.i("a==" + a);
//
//        }
//        LogUtils.i("a==" + a);
//        try {
//            Process process = Runtime.getRuntime().exec("java -version");
//            BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//            String line = br.readLine();
//            System.out.println("line=="+line);
//            LogUtils.i("line=="+line);
//            process.destroy();
//        } catch (IOException e) {
//            e.printStackTrace();
//            LogUtils.i("error==");
//        }

//        try {
//            String a = "dfsdf";
//            assert a.length() > 13;
//        assert a.length()>13:"slkjdflksj==";
//        } catch (Error error) {
////            NullPointerException
////            MalformedJsonException
////            UnsupportedOperationException
//            LogUtils.i(error.toString());
////            ArrayIndexOutOfBoundsException
////            InputMismatchException
////            EOFException
////            MalformedURLException
////            IllegalArgumentException
////            ArithmeticException
////            NumberFormatException
//        }
//        LogUtils.i(Character.toUpperCase('d') + "==");
//        LogUtils.i(Character.isLetter('d') + "==");
//        LogUtils.i(Character.isDigit('d') + "==");

//        equalPoetry();
//        LogUtils.i(tryFinally());
//        ArrayList
//        collectionsTest();
//        stringTest();
//        hashCollision();
    }

    private void mathTest() {
        LogUtils.i("abs()：绝对值。==" + Math.abs(-19));
        LogUtils.i("pow(a,n)：a的n次方幂。==" + Math.pow(2, 4));
        LogUtils.i("ceil()：向上取整==" + Math.ceil(10.7));
        LogUtils.i("floor()：向下取整==" + Math.floor(10.7));
        LogUtils.i("round()：四舍五入。==" + Math.round(10.6));
        LogUtils.i("random()：随机数。==" + Math.random());
        LogUtils.i("min()：两个数的最小值。==" + Math.min(10, 11));
        LogUtils.i("max()：两个数的最大值。==" + Math.max(10, 11));
        LogUtils.i("sqrt()：求平方根。==" + Math.sqrt(16));
        LogUtils.i("sin()：正弦值。==" + Math.sin(30));
        LogUtils.i("cos()：余弦值。==" + Math.cos(30));
        LogUtils.i("tan()：正切值。==" + Math.tan(30));
        LogUtils.i("rint()：取最近的整数。他妈的，变相的四舍五入，但是10.5会舍弃。==" + Math.rint(10.51));
        LogUtils.i("exp()：以自然数底数e的n次方。==" + Math.exp(2));
        LogUtils.i("log()：以e为底数的对数值。==" + Math.log(100));
        LogUtils.i("log10()：以10为底的对数值。==" + Math.log10(1000));
        LogUtils.i("asin()：反正弦值。==" + Math.asin(30));
        LogUtils.i("acos()：反余弦值。==" + Math.acos(30));
        LogUtils.i("atan()：反正切值。==" + Math.atan(30));
        LogUtils.i("atan2()：将笛卡尔坐标转换为极坐标，并返回极坐标的角度值。==" + Math.atan2(30, 30));
        LogUtils.i("toDegrees()：将参数转化为角度。==" + Math.toDegrees(1));
        LogUtils.i("toRadians()：将角度转换为弧度。==" + Math.toRadians(30));
    }

    //hash 碰撞的算法
    private void hashCollision() {
        int a = 55;     //110111
        int b = 16;     //010000
        int c = 16 - 1; //001111
        int d = 63;     //111111
        LogUtils.i("a==" + Integer.toBinaryString(a));
        LogUtils.i("b==" + Integer.toBinaryString(b));
        LogUtils.i("c==" + Integer.toBinaryString(c));
        LogUtils.i("d==" + Integer.toBinaryString(d));
        LogUtils.i("a & b==" + (a & b));//16
        LogUtils.i("a % b==" + (a % b));//7
        LogUtils.i("a & c==" + (a & c));//7
        LogUtils.i("a % c==" + (a % c));//10
        LogUtils.i("d & c==" + (d & c));//15
    }

    String d;

    private void stringTest() {
        String a = "abc";
        String b = "abc";
        String c = new String("abc");
        d = new String("abc");
        LogUtils.i("字符串测试==");
        LogUtils.i("a==b ==" + (a == b));//字符串a与b比较==true
        LogUtils.i("a==c ==" + (a == c));//字符串a与对象c比较==false
        LogUtils.i("c==d ==" + (c == d));//对象c 与对象d比较==false
        LogUtils.i("a.equals(c) ==" + (a.equals(c)));//字符串a与对象c 的 equals == true
        LogUtils.i("c.equals(d) ==" + (c.equals(d)));//对象c 与对象d 的 equals == true
    }

    private void collectionsTest() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("q");
        list.add("w");
        list.add("e");
        list.add("r");
        list.add("t");
        list.add("y");
        list.add("u");
        list.add("i");
        list.add("d");
        list.add("c");
        list.add("v");
        LogUtils.i("Collections 排序方法测试");
        LogUtils.i("list原顺序==" + list);
        LogUtils.i("String 已实现 Comparable接口");
        Collections.sort(list);
        LogUtils.i("list的sort排序，默认是升序==" + list);
        Collections.reverse(list);
        LogUtils.i("list的reverse 翻转排序==" + list);
        Collections.shuffle(list);
        LogUtils.i("list的shuffle 随机排序==" + list);
//        Collections.sort(list, String::compareTo);//我靠，简单是够简单了，但这他妈谁看的懂啊。
        Collections.sort(list, (o1, o2) -> {
            return o1.compareTo(o2);
        });
        LogUtils.i("sort 的 comparator 中 o1.compareTo(o2) 排序是升序==" + list);
        Collections.sort(list, (o1, o2) -> o2.compareTo(o1));
        LogUtils.i("sort 的 comparator 中 o2.compareTo(o1) 排序是降序==" + list);
        Collections.sort(list, (o1, o2) -> Collator.getInstance().compare(o1, o2));
        LogUtils.i("sort 的 comparator 中用 Collator 中的compare(o1,o2) 排序是升序==" + list);
        Collections.sort(list, (o1, o2) -> Collator.getInstance().compare(o2, o1));
        LogUtils.i("sort 的 comparator 中用 Collator 中的compare(o2,o1) 排序是降序==" + list);

    }

    private String tryFinally() {
        try {
            assert 5 > 2;
            LogUtils.i("try执行");
            return "try执行";
        } catch (AssertionError e) {

        } finally {
            return "finally执行";
        }
    }

    private void equalPoetry() {
//        presenter.calculationTextLength();

        String poetry = FileUtils.readAssestToChar(this, "Documents/背诵.txt")
//        String poetry = FileUtils.readAssestToChar(this, "Documents/复写.txt")
                .replaceAll("　", "")
                .replaceAll("\n", "");
        LogUtils.i("长度==" + (poetry.length()));
        int aa =
                115
                        + 70
                        + 38
                        + 61
                        + 43
                        + 56
                        + 50
                        + 129
                        + 136
                        + 21;
        LogUtils.i("长度==" + aa);
        //        Character
//        Boolean
//        Number
//Math

//        String text = getResources().getString(R.string.new_text).replaceAll(" ", "");
//        LogUtils.i("长度==" + (text.length()));
//        String origin = getResources().getString(R.string.origin).replaceAll(" ", "");
//        LogUtils.i("长度==" + (origin.length()));
//        String[] texts = text.split("");
//        String[] origins = origin.split("");
//        for (int i = 2; i < texts.length - 2; i++) {
//            if (!texts[i].equals(origins[i])) {
////                LogUtils.i(texts[i - 2] + texts[i - 1] + texts[i] + texts[i + 1] + texts[i + 2] + "==" + origins[i - 2] + origins[i - 1] + origins[i] + origins[i + 1]+origins[i + 2]);
////                i += 2;
//                LogUtils.i(i + texts[i] + "==" + origins[i]);
//            }
//        }
    }

    public void outPutException(Exception e) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        Throwable cause = e.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        LogUtils.i("mqtt+" + result);
    }

    public void list(List<Object> list) {
//        ThreadLocal
//        DelayQueue
//        Executors
//        Executor
//        Lock
//        RUUR'U'RUUL'UR'U'L
    }
}