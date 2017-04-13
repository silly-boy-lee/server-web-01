package com.cmsg.util;

import java.util.Date;

import org.apache.commons.lang3.time.FastDateFormat;

import com.cmsg.constant.SystemConstant;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

/**
 * ClassName: TokenUtil <br/>
 * Function: Token生成工具类<br/>
 * date: 2016年9月20日 下午1:53:47 <br/>
 * 
 * @author Mr.Lee
 * @version
 */
public class TokenUtil {

	/** 
	 * privateKey: token加密私钥
	 */ 
	private static final String privateKey = "cmsg2016";

	/** 
	 * getToken: 以密码和日期字符串生成一个token . <br/>
	 *
	 * @author Mr.Lee
	 * @param password 密码
	 * @param date 日期字符串
	 * @return token
	 */ 
	public static String getToken(String password, String date) {
		return Hashing.md5().newHasher().putString(password, Charsets.UTF_8)
				.putString(privateKey, Charsets.UTF_8)
				.putString(date, Charsets.UTF_8).hash().toString();
	}

	/** 
	 * getToken: 以密码和日期字符串生成一个token. <br/>
	 *
	 * @author Mr.Lee
	 * @param password 密码
	 * @param date 日期
	 * @return token
	 */ 
	public static String getToken(String password, Date date) {
		return Hashing.md5().newHasher().putString(password, Charsets.UTF_8)
				.putString(privateKey, Charsets.UTF_8)
				.putString(getDate(date), Charsets.UTF_8).hash().toString();
	}

	/** 
	 * getToken: 以密码和当前日期字符串生成一个token. <br/>
	 *
	 * @author Mr.Lee
	 * @param password 密码
	 * @return token
	 */ 
	public static String getToken(String password) {
		return Hashing.md5().newHasher().putString(password, Charsets.UTF_8)
				.putString(privateKey, Charsets.UTF_8)
				.putString(getDate(), Charsets.UTF_8).hash().toString();
	}

	/** 
	 * validToken: 验证token. <br/>
	 *
	 * @author Mr.Lee
	 * @param token token
	 * @param password 密码
	 * @return true 则验证通过 false 则验证失败
	 */ 
	public static boolean validToken(String token, String password) {
		String confirm = getToken(password);
		return confirm.equals(token);
	}

	/** 
	 * getDate: 以YYYYMMDDHH获取当前日期组成的字符串. <br/>
	 *
	 * @author Mr.Lee
	 * @return 返回YYYYMMDDHH获取当前日期组成的字符串
	 */ 
	public static String getDate() {
		Date date = new Date(System.currentTimeMillis());
		return FastDateFormat.getInstance("yyyyMMddHH").format(date);

	}

	/** 
	 * getDate: 以YYYYMMDDHH获取当前日期组成的字符串. <br/>
	 *
	 * @author Mr.Lee
	 * @param now 当前日期
	 * @return 返回日期字符串
	 */ 
	public static String getDate(Date now) {
		return FastDateFormat.getInstance("yyyyMMddHH").format(now);
	}

	/** 
	 * getNextHour: 取当前日期的下一个时间点（相隔一小时）. <br/>
	 *
	 * @author Mr.Lee
	 * @param now 当前日期
	 * @return 返回日期字符串
	 */ 
	public static String getNextHour(Date now) {
		Date date = new Date(now.getTime() + SystemConstant.TOKEN_TIME_OUT);
		return FastDateFormat.getInstance("yyyyMMddHH").format(date);

	}

}
