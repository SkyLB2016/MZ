package com.sky.oa.vm;

import android.Manifest;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.RequiresPermission;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;

import com.sky.common.utils.FileUtils;
import com.sky.common.utils.LogUtils;
import com.sky.oa.entity.KeyValue;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/10 5:44 下午
 * @Version: 1.0
 */
public class MainVM extends ViewModel {
    boolean iscompletedTask = true;
    boolean iscompleted;

    public void TestMethod(Context context) {
//        String[] texts = new String[5];

//        LogUtils.i("iscompletedTask==" + texts[1]);
//        LogUtils.i("iscompletedTask==" + texts[7]);
//        LogUtils.i("iscompletedTask==" + iscompletedTask);
//        LogUtils.i("iscompleted==" + iscompleted);
//
//        LogUtils.i("onbackpress==");
//        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALENDAR);
//        LogUtils.i("日历 ；READ_CALENDAR的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_CALENDAR);
//        LogUtils.i("日历 ；WRITE_CALENDAR的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
//        LogUtils.i("相机 ；CAMERA的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS);
//        LogUtils.i("联系人 ；READ_CONTACTS的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_CONTACTS);
//        LogUtils.i("联系人 ；WRITE_CONTACTS的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.GET_ACCOUNTS);
//        LogUtils.i("联系人 ；GET_ACCOUNTS的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
//        LogUtils.i("位置 ；ACCESS_FINE_LOCATION的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
//        LogUtils.i("位置 ；ACCESS_COARSE_LOCATION的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO);
//        LogUtils.i("麦克风 ；RECORD_AUDIO的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
//        LogUtils.i("电话 ；READ_PHONE_STATE的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);
//        LogUtils.i("电话 ；CALL_PHONE的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG);
//        LogUtils.i("电话 ；READ_CALL_LOG的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_CALL_LOG);
//        LogUtils.i("电话 ；WRITE_CALL_LOG的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.ADD_VOICEMAIL);
//        LogUtils.i("电话 ；ADD_VOICEMAIL的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.USE_SIP);
//        LogUtils.i("电话 ；USE_SIP的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.PROCESS_OUTGOING_CALLS);
//        LogUtils.i("电话 ；PROCESS_OUTGOING_CALLS的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.BODY_SENSORS);
//        LogUtils.i("传感器 ；BODY_SENSORS的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS);
//        LogUtils.i("短信 ；SEND_SMS的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_SMS);
//        LogUtils.i("短信 ；RECEIVE_SMS的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_SMS);
//        LogUtils.i("短信 ；READ_SMS的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_WAP_PUSH);
//        LogUtils.i("短信 ；RECEIVE_WAP_PUSH的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_MMS);
//        LogUtils.i("短信 ；RECEIVE_MMS的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
//        LogUtils.i("存储 ；READ_EXTERNAL_STORAGE的权限==" + permissionCheck);
//        permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        LogUtils.i("存储 ；WRITE_EXTERNAL_STORAGE的权限==" + permissionCheck);
//        LogUtils.i(" ；getDeviceId(context)==" + getDeviceId(context));

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
//        getDeviceId(context);

//        List<KeyValue> list = new ArrayList<>();
//        list.add(new KeyValue("1", "2"));
//        list.add(new KeyValue("1", "2"));
//        list.add(new KeyValue("1", "2"));
//        list.add(new KeyValue("1", "2"));
//        list.add(new KeyValue("1", "2"));
//        list.add(new KeyValue("1", "2"));
//        list.add(new KeyValue("1", "2"));
//        list.add(new KeyValue("1", "2"));
//        LogUtils.i(context.getFilesDir().getAbsolutePath());
//        FileUtils.serialize(context.getFilesDir().getAbsolutePath() + "/data", list);
//        FileUtils.serialize(context.getFilesDir().getAbsolutePath() + "/data1", new KeyValue("1", "2"));
//
        double distance = lineDistance(39.930713, 116.385936, 39.930709, 116.38589);
        LogUtils.i("两点间的距离==" + distance);


        String address = "河北省保定市竞秀区万博广场";
        int start = address.indexOf("省") + 1;
        address = address.substring(start , address.indexOf("市") + 1);
        LogUtils.i("截取出来的字段1为==" + address);
        address = "北京市海淀区圣熙八号";
        start = address.indexOf("省") + 1;
        int end = address.indexOf("市");
        end = address.indexOf("市");
        address = address.substring(start , address.indexOf("市") + 1);
        LogUtils.i("截取出来的字段1为==" + address);
        address = "北京市海淀区圣熙八号";
        address = address.substring(0, 0);
        LogUtils.i("截取出来的字段1为==" + address);
        LogUtils.i("截取出来的字段1为==" + start);


    }

    public static double rad(double d) {
        return d * Math.PI / 180;
    }

    public static double lineDistance(double startLat, double startLng, double endLat, double endLng) {
        double R = 6378137;

        startLat = rad(startLat);
        startLng = rad(startLng);
        endLat = rad(endLat);
        endLng = rad(endLng);

        if (startLat < 0) {
            startLat = Math.PI / 2 + Math.abs(startLat);
        }
        if (startLat > 0) {
            startLat = Math.PI / 2 - Math.abs(startLat);
        }
        if (startLng < 0) {
            startLng = Math.PI * 2 - Math.abs(startLng);
        }

        if (endLat < 0) {
            endLat = Math.PI / 2 + Math.abs(endLat);
        }
        if (endLat > 0) {
            endLat = Math.PI / 2 - Math.abs(endLat);
        }
        if (endLng < 0) {
            endLng = Math.PI * 2 - Math.abs(endLng);
        }

        double x1 = R * Math.cos(startLng) * Math.sin(startLat);
        double y1 = R * Math.sin(startLng) * Math.sin(startLat);
        double z1 = R * Math.cos(startLat);

        double x2 = R * Math.cos(endLng) * Math.sin(endLat);
        double y2 = R * Math.sin(endLng) * Math.sin(endLat);
        double z2 = R * Math.cos(endLat);


        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));
        double cos = (R * R + R * R - d * d) / (2 * R * R);
        double theta = Math.acos(cos);
        return theta * R;
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

    public void setCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        LogUtils.i("Calendar.ERA==" + Calendar.ERA + "==" + calendar.get(Calendar.ERA));
        LogUtils.i("Calendar.YEAR==" + Calendar.YEAR + "==" + calendar.get(Calendar.YEAR));
        //MONTH 的数值是从0到11，一月份是0.
        LogUtils.i("Calendar.MONTH==" + Calendar.MONTH + "==" + calendar.get(Calendar.MONTH));
        //WEEK_OF_YEAR 年尾跨年周，只算是下年的第一周，不算本年的最后一周。
        LogUtils.i("Calendar.WEEK_OF_YEAR==" + Calendar.WEEK_OF_YEAR + "==" + calendar.get(Calendar.WEEK_OF_YEAR));
        //WEEK_OF_MONTH 跨月周，既是本月的最后一周，也是下月的第一周。
        LogUtils.i("Calendar.WEEK_OF_MONTH==" + Calendar.WEEK_OF_MONTH + "==" + calendar.get(Calendar.WEEK_OF_MONTH));
        LogUtils.i("Calendar.DATE==" + Calendar.DATE + "==" + calendar.get(Calendar.DATE));
        LogUtils.i("Calendar.DAY_OF_MONTH==" + Calendar.DAY_OF_MONTH + "==" + calendar.get(Calendar.DAY_OF_MONTH));
        LogUtils.i("Calendar.DAY_OF_YEAR==" + Calendar.DAY_OF_YEAR + "==" + calendar.get(Calendar.DAY_OF_YEAR));
        //本周周几，统计是从周日到周六，数值是从1到7。周日是1，周六是7
        LogUtils.i("Calendar.DAY_OF_WEEK==" + Calendar.DAY_OF_WEEK + "==" + calendar.get(Calendar.DAY_OF_WEEK));
        LogUtils.i("Calendar.DAY_OF_WEEK_IN_MONTH==" + Calendar.DAY_OF_WEEK_IN_MONTH + "==" + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        //12小时制
        LogUtils.i("Calendar.HOUR==" + Calendar.HOUR + "==" + calendar.get(Calendar.HOUR));
        //24小时制
        LogUtils.i("Calendar.HOUR_OF_DAY==" + Calendar.HOUR_OF_DAY + "==" + calendar.get(Calendar.HOUR_OF_DAY));
        LogUtils.i("Calendar.MINUTE==" + Calendar.MINUTE + "==" + calendar.get(Calendar.MINUTE));
        LogUtils.i("Calendar.SECOND==" + Calendar.SECOND + "==" + calendar.get(Calendar.SECOND));
        LogUtils.i("Calendar.MILLISECOND==" + Calendar.MILLISECOND + "==" + calendar.get(Calendar.MILLISECOND));
        LogUtils.i("Calendar.ZONE_OFFSET==" + Calendar.ZONE_OFFSET + "==" + calendar.get(Calendar.ZONE_OFFSET));
        LogUtils.i("Calendar.DST_OFFSET==" + Calendar.DST_OFFSET + "==" + calendar.get(Calendar.DST_OFFSET));
        LogUtils.i("Calendar.firstDayOfWeek==" + calendar.getFirstDayOfWeek());
        LogUtils.i("Calendar.minimalDaysInFirstWeek==" + calendar.getMinimalDaysInFirstWeek());
    }

    private String substringWeek(String time) {
        return time.substring(0, time.indexOf("("));
    }

}