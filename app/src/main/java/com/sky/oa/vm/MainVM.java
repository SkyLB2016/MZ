package com.sky.oa.vm;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.RequiresPermission;
import androidx.lifecycle.ViewModel;

import com.sky.common.utils.FileUtils;
import com.sky.common.utils.LogUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/10 5:44 下午
 * @Version: 1.0
 */
public class MainVM extends ViewModel {
    public void TestMethod(Context context) {
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
        getDeviceId(context);
    }

    @RequiresPermission(value = "android.permission.READ_PRIVILEGED_PHONE_STATE")
    public String getDeviceId(Context context) {
        String deviceId;
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = tm.getDeviceId();
        } catch (Exception ignore) {
            deviceId = "";
        }
        LogUtils.i("deviceId==" + deviceId);
        if (TextUtils.isEmpty(deviceId)) {
//            deviceId = getUDID();
        }
        return deviceId;
    }

    private void mathTest() {
        LogUtils.i("abs()：绝对值。==" + Math.abs(-19));
        LogUtils.i("pow(a,n)：a的n次方幂。==" + Math.pow(2, 4));
        LogUtils.i("ceil()：向上取整==" + Math.ceil(10.7));
        LogUtils.i("floor()：向下取整==" + Math.floor(10.7d));
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

    private void equalPoetry(Context context) {
//        presenter.calculationTextLength();

        String poetry = FileUtils.readAssestToChar(context, "Documents/背诵.txt")
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