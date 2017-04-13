package com.cmsg.util;

import java.util.Random;

/**
 * ClassName: IDUtils <br/>
 * Function: id生成工具类 <br/>
 * date: 2016年9月13日 下午7:49:34 <br/>
 * 
 * @author Mr.Lee
 * @version
 */
public class IDUtils {

	 
	/** 
	 * genImageName:生成图片名. <br/>
	 *
	 * @author Administrator 
	 * @return 返回生成的图片文件名
	 */ 
	public static String genImageName() {
		// 取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		// long millis = System.nanoTime();
		// 加上三位随机数
		Random random = new Random();
		int end3 = random.nextInt(999);
		// 如果不足三位前面补0
		String str = millis + String.format("%03d", end3);

		return str;
	}

	/** 
	 * genItemId:商品id生成. <br/>
	 *
	 * @author Administrator 
	 * @return 返回生成的商品ID
	 */ 
	public static long genItemId() {
		// 取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		// long millis = System.nanoTime();
		// 加上两位随机数
		Random random = new Random();
		int end2 = random.nextInt(99);
		// 如果不足两位前面补0
		String str = millis + String.format("%02d", end2);
		long id = new Long(str);
		return id;
	}

	/** 
	 * main:测试方法. <br/>
	 *
	 * @author Administrator 
	 * @param args 
	 */ 
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++){
			System.out.println(genItemId());
		}
	}
}
