package com.sky.common.utils;

import java.util.regex.Pattern;

/**
 * Created by SKY on 2017/3/30.
 * 正则表达式
 */
public class RegexUtils {

    //1.邮箱
    private static final String EMAIL = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
    //private static final String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    //2.域名
    private static final String DOMAINNAME = "[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(/.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+/.?";
    //3.InternetURL
    private static final String InternetURL = "[a-zA-z]+://[^\\s]*";
    private static final String InternetURL2 = "^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";
    //4.手机号码
    //private static final String PHONE = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|17[0|1|2|3|5|6|7|8]|18[0|1|2|3|4|5|6|7|8|9])\\d{8}$";
    private static final String PHONE = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|166|17[0|1|2|3|5|6|7|8]|18[0|1|2|3|4|5|6|7|8|9]|19[8|9])\\d{8}$";
    //5.电话号码("XXX-XXXXXXX"、"XXXX-XXXXXXXX"、"XXX-XXXXXXX"、"XXX-XXXXXXXX"、"XXXXXXX"和"XXXXXXXX)：
    private static final String TELPHONE = "^(\\(\\d{3,4}-)|\\d{3.4}-)?\\d{7,8}$";
    //6.国内电话号码(0511-4405222、021-87888822)：\d{3}-\d{8}|\d{4}-\d{7}
    private static final String TELPHONE2 = "\\d{3}-\\d{8}|\\d{4}-\\d{7}";
    //7 身份证号(15位、18位数字)：
    private static final String IDNUMBER = "^\\d{15}|\\d{18}$";
    //8 短身份证号码(数字、字母x结尾)： 或
    private static final String IDNUMBER1 = "^([0-9]){7,18}(x|X)?$";
    private static final String IDNUMBER2 = "^\\d{8,18}|[0-9x]{8,18}|[0-9X]{8,18}?$";

//9 帐号是否合法(字母开头，允许5-16字节，允许字母数字下划线)：^[a-zA-Z][a-zA-Z0-9_]{4,15}$
//10 密码(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)：^[a-zA-Z]\w{5,17}$
//11 强密码(必须包含大小写字母和数字的组合，不能使用特殊字符，长度在8-10之间)：^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$
//12 日期格式：^\d{4}-\d{1,2}-\d{1,2}
//13 一年的12个月(01～09和1～12)：^(0?[1-9]|1[0-2])$
//14 一个月的31天(01～09和1～31)：^((0?[1-9])|((1|2)[0-9])|30|31)$
//15 钱的输入格式：
//16 1.有四种钱的表示形式我们可以接受:"10000.00" 和 "10,000.00", 和没有 "分" 的 "10000" 和 "10,000"：^[1-9][0-9]*$
//17 2.这表示任意一个不以0开头的数字,但是,这也意味着一个字符"0"不通过,所以我们采用下面的形式：^(0|[1-9][0-9]*)$
//18 3.一个0或者一个不以0开头的数字.我们还可以允许开头有一个负号：^(0|-?[1-9][0-9]*)$
//19 4.这表示一个0或者一个可能为负的开头不为0的数字.让用户以0开头好了.把负号的也去掉,因为钱总不能是负的吧.下面我们要加的是说明可能的小数部分：^[0-9]+(.[0-9]+)?$
//20 5.必须说明的是,小数点后面至少应该有1位数,所以"10."是不通过的,但是 "10" 和 "10.2" 是通过的：^[0-9]+(.[0-9]{2})?$
//21 6.这样我们规定小数点后面必须有两位,如果你认为太苛刻了,可以这样：^[0-9]+(.[0-9]{1,2})?$
//22 7.这样就允许用户只写一位小数.下面我们该考虑数字中的逗号了,我们可以这样：^[0-9]{1,3}(,[0-9]{3})*(.[0-9]{1,2})?$
//23 8.1到3个数字,后面跟着任意个 逗号+3个数字,逗号成为可选,而不是必须：^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,2})?$
//24 备注：这就是最终结果了,别忘了"+"可以用"*"替代如果你觉得空字符串也可以接受的话(奇怪,为什么?)最后,别忘了在用函数时去掉去掉那个反斜杠,一般的错误都在这里
//25 xml文件：^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\.[x|X][m|M][l|L]$
//26 中文字符的正则表达式：[\u4e00-\u9fa5]
//27 双字节字符：[^\x00-\xff] (包括汉字在内，可以用来计算字符串的长度(一个双字节字符长度计2，ASCII字符计1))
//28 空白行的正则表达式：\n\s*\r (可以用来删除空白行)
//29 HTML标记的正则表达式：<(\S*?)[^>]*>.*?</\1>|<.*? /> (网上流传的版本太糟糕，上面这个也仅仅能部分，对于复杂的嵌套标记依旧无能为力)
//30 首尾空白字符的正则表达式：^\s*|\s*$或(^\s*)|(\s*$) (可以用来删除行首行尾的空白字符(包括空格、制表符、换页符等等)，非常有用的表达式)
//31 腾讯QQ号：[1-9][0-9]{4,} (腾讯QQ号从10000开始)
//32 中国邮政编码：[1-9]\d{5}(?!\d) (中国邮政编码为6位数字)
//33 IP地址：\d+\.\d+\.\d+\.\d+ (提取IP地址时有用)
//34 IP地址：((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))
    //车牌号
    public static final String CARDNUM = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";
    public static final String CARNO = "^[\u4e00-\u9fff]{1}[A-Z]{1}[A-Z0-9]{5,6}$";

    public static final Pattern PATTERN_CARNUM = Pattern.compile(CARDNUM);
    public static final Pattern PATTERN_PHONE = Pattern.compile(PHONE);
    public static final Pattern PATTERN_IDNUMBER = Pattern.compile(IDNUMBER);
    public static final Pattern PATTERN_IDNUMBER1 = Pattern.compile(IDNUMBER1);
    public static final Pattern PATTERN_IDNUMBER2 = Pattern.compile(IDNUMBER2);
    public static final Pattern PATTERN_EMAIL = Pattern.compile(EMAIL);
    public static final Pattern PATTERN_CARNO = Pattern.compile(CARNO);

    /**
     * @param source  需要判断的字符串
     * @param pattern 正则规则
     * @return 符合返回true
     */
    public static boolean matches(String source, String pattern) {
        return Pattern.matches(pattern, source);
    }

    /**
     * @param source  需要判断的字符串
     * @param pattern 正则规则
     * @return 符合返回true
     */
    public static boolean matches(String source, Pattern pattern) {
        return pattern.matcher(source).matches();
    }

    /**
     * @param source 手机号
     * @return 手机号是否正确
     */
    public static boolean isPhone(String source) {
        return matches(source, PATTERN_PHONE);
    }

    /**
     * @param source 邮箱账号
     * @return 邮箱是否正确
     */
    public static boolean isEmail(String source) {
        return matches(source, PATTERN_EMAIL);
    }

    /**
     * @param source 身份证号
     * @return 身份证是否正确
     */
    public static boolean isIdNumber(String source) {
        return matches(source, PATTERN_IDNUMBER) || matches(source, PATTERN_IDNUMBER1) || matches(source, PATTERN_IDNUMBER2);
    }

    /**
     * @param source 车牌号
     * @return 车牌号是否正确
     */
    public static boolean isCarNum(String source) {
        return matches(source, PATTERN_CARNUM);
    }

    public static boolean isCarNo(String source) {
        return matches(source, PATTERN_CARNO);
    }
}
