package com.cmsg.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: RegexUtils <br/>
 * Function: 正则工具类 <br/>
 * date: 2016年9月18日 上午9:14:08 <br/>
 * 
 * @author Mr.Lee
 * @version
 */
public class RegexUtils {

	/**
	 * checkEmail: 验证邮箱. <br/>
	 * 
	 * @author Mr.Lee
	 * @param email
	 *            email地址，格式：zhangsan@zuidaima.com，zhangsan@xxx.com.cn，
	 *            xxx代表邮件服务商
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkEmail(String email) {
		String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
		return Pattern.matches(regex, email);
	}

	/** 
	 * checkIdCard:  验证身份证号码. <br/>
	 *
	 * @author Mr.Lee
	 * @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
	 * @return 验证成功返回true，验证失败返回false
	 */ 
	public static boolean checkIdCard(String idCard) {
		String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
		return Pattern.matches(regex, idCard);
	}
	
	/** 
	 * checkMobile: 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））. <br/>
	 *
	 * @author Mr.Lee
	 * @param mobile 移动、联通、电信运营商的号码段
	 * <p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡),
	 * 、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
	 * <p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p> 
	 * <p>电信的号段：133、153、180（未启用）、189</p>
	 *  
	 * @return 验证成功返回true，验证失败返回false
	 */ 
	public static boolean checkMobile(String mobile) {   
        String regex = "(\\+\\d+)?1[3458]\\d{9}$";   
        return Pattern.matches(regex,mobile);   
    }
	
    /** 
     * checkPhone: 验证固定电话号码 . <br/>
     *
     * @author Mr.Lee
     * @param phone 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
     * <p><b>国家（地区） 代码 ：</b>标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
     * 数字之后是空格分隔的国家（地区）代码。</p> 
     * <p><b>区号（城市代码）：</b>这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
     * 对不使用地区或城市代码的国家（地区），则省略该组件。</p>
     * <p><b>电话号码：</b>这包含从 0 到 9 的一个或多个数字 </p>
     * 
     * @return 验证成功返回true，验证失败返回false 
     */ 
    public static boolean checkPhone(String phone) {   
        String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";   
        return Pattern.matches(regex, phone);   
    } 
       
    /** 
     * checkDigit: 验证整数（正整数和负整数）. <br/>
     *
     * @author Mr.Lee
     * @param digit 一位或多位0-9之间的整数 
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkDigit(String digit) {   
        String regex = "\\-?[1-9]\\d+";   
        return Pattern.matches(regex,digit);   
    }   
       
    /** 
     * checkDecimals:验证整数和浮点数（正负整数和正负浮点数） . <br/>
     *
     * @author Mr.Lee
     * @param decimals decimals 一位或多位0-900之间的浮点数，如：1.23，233.30
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkDecimals(String decimals) {   
        String regex = "\\-?[1-9]\\d+(\\.\\d+)?";   
        return Pattern.matches(regex,decimals);   
    }    
      
    /** 
     * checkBlankSpace: 验证空白字符. <br/>
     *
     * @author Mr.Lee
     * @param blankSpace 空白字符，包括：空格、\t、\n、\r、\f、\x0B
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkBlankSpace(String blankSpace) {   
        String regex = "\\s+";   
        return Pattern.matches(regex,blankSpace);   
    }   
       
    /** 
     * checkChinese: 验证中文字符 . <br/>
     *
     * @author Mr.Lee
     * @param chinese 中文字符 
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkChinese(String chinese) {   
        String regex = "^[\u4E00-\u9FA5]+$";   
        return Pattern.matches(regex,chinese);   
    }  
       
    /** 
     * checkBirthday:  验证日期（年月日）. <br/>
     *
     * @author Mr.Lee
     * @param birthday 日期，格式：1992-09-03，或1992.09.03 
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkBirthday(String birthday) {   
        String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";   
        return Pattern.matches(regex,birthday);   
    }
       
    /** 
     * checkURL: 验证URL地址. <br/>
     *
     * @author Mr.Lee
     * @param url 格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或 http://www.csdn.net:80 
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkURL(String url) {   
        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";   
        return Pattern.matches(regex, url);   
    }
      
    /** 
     * getDomain: 获取网址 URL的一级域名 . <br/>
     *
     * @author Mr.Lee
     * @param url 网址(如:http://www.zuidaima.com/share/1550463379442688.htm)
     * @return 一级域名 如: zuidaima.com
     */ 
    public static String getDomain(String url) {  
        Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);  
        Matcher matcher = p.matcher(url);  
        matcher.find();  
        return matcher.group();  
    }
    
    /** 
     * getFullDomain: 获取网址 URL的完整域名. <br/>
     *
     * @author Mr.Lee
     * @param url url 网址(如:http://www.zuidaima.com/share/1550463379442688.htm)
     * @return 完整域名
     */ 
    public static String getFullDomain(String url) {  
        Pattern p=Pattern.compile("[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);  
        Matcher matcher = p.matcher(url);  
        matcher.find();  
        return matcher.group();  
    }
    
    /** 
     * checkPostcode: 匹配中国邮政编码 . <br/>
     *
     * @author Mr.Lee
     * @param postcode 邮政编码
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkPostcode(String postcode) {   
        String regex = "[1-9]\\d{5}";   
        return Pattern.matches(regex, postcode);   
    }
     
    /** 
     * checkIpAddress: 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小). <br/>
     *
     * @author Mr.Lee
     * @param ipAddress IPv4标准地址
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkIpAddress(String ipAddress) {   
        String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";   
        return Pattern.matches(regex, ipAddress);   
    }
    /**
     * 
     * checkPassword:匹配1-16位 字母 数字 下划线组成的密码. <br/>
     *
     * @author lh
     * @param password 用户密码
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkPassword(String password) {   
    	String regex = "^[_0-9a-z]{6,16}$";   
    	return Pattern.matches(regex, password);   
    }

}
