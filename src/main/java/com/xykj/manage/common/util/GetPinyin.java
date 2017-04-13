package com.cmsg.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/** 
 * ClassName: GetPinyin <br/> 
 * Function: 汉字拼音处理工具类 <br/> 
 * date: 2016年9月18日 上午8:49:07 <br/> 
 * 
 * @author Mr.Lee 
 * @version  
 */ 
public class GetPinyin {

	/** 
	 * getPingYin: 得到汉字的全拼 . <br/>
	 *
	 * @author Mr.Lee
	 * @param src 汉字字符串
	 * @return 汉字的全拼音
	 */ 
	public static String getPingYin(String src) {
		char[] t1 = null;
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断是否为汉字字符
				if (java.lang.Character.toString(t1[i]).matches(
						"[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					t4 += t2[0];
				} else {
					t4 += java.lang.Character.toString(t1[i]);
				}
			}
			return t4;
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			e1.printStackTrace();
		}
		return t4;
	}

	/** 
	 * getPinYinHeadChar: 得到中文首字母 . <br/>
	 *
	 * @author Mr.Lee
	 * @param str 汉字字符串
	 * @return 汉字的简拼
	 */ 
	public static String getPinYinHeadChar(String str) {

		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}

	/** 
	 * getCnASCII: 将字符串转换为ASCII码 . <br/>
	 *
	 * @author Mr.Lee
	 * @param cnStr 汉字字符串
	 * @return ASCII码
	 */ 
	public static String getCnASCII(String cnStr) {
		StringBuffer strBuf = new StringBuffer();
		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
		}
		return strBuf.toString();
	}

	/** 
	 * main: 测试方法 . <br/>
	 *
	 * @author Mr.Lee
	 * @param args 主方法参数
	 */ 
	public static void main(String[] args) {
		String cnStr = "中国";
		System.out.println(getPingYin(cnStr));
		System.out.println(getPinYinHeadChar(cnStr));
		System.out.println(getCnASCII(cnStr));
	}

}
