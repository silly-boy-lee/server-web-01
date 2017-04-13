package com.cmsg.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/** 
 * ClassName: PublicUtil <br/> 
 * Function: 常规处理工具类 <br/> 
 * date: 2016年9月18日 上午10:20:39 <br/> 
 * 
 * @author Mr.Lee 
 * @version  
 */ 
public class PublicUtil {
	
	 
	/** 
	 * getRandomNum: 生成六位数验证码. <br/>
	 *
	 * @author Mr.Lee
	 * @return 验证码
	 */ 
	public static int getRandomNum(){
		 Random r = new Random();
		 return r.nextInt(900000)+100000;//(Math.random()*(999999-100000)+100000)
	}
	
	/** 
	 * getPorjectPath: 取工程的根路径. <br/>
	 *
	 * @author Mr.Lee
	 * @return 工程的根路径
	 */ 
	public static String getProjectPath(){
		String nowpath = "";
		nowpath=System.getProperty("user.dir")+"/";
		return nowpath;
	}
	
	/** 
	 * getIp: 获取本机访问地址. <br/>
	 *
	 * @author Mr.Lee
	 * @return 本机访问地址
	 */ 
	public static String getIp(){
		String ip = "";
		try {
			InetAddress inet = InetAddress.getLocalHost();
			ip = inet.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return ip;
	}
	
	/** 
	 * readTxtFile:读取txt里的单行内容 . <br/>
	 *
	 * @author Mr.Lee
	 * @param fileP 文件路径
	 * @return String
	 */ 
	public static String readTxtFile(String fileP) {
		try {
			
			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if(filePath.indexOf(":") != 1){
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { 		// 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
				new FileInputStream(file), encoding);	// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			}else{
				System.out.println("找不到指定的文件,查看此路径是否正确:"+filePath);
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		return "";
	}
	
	/** 
	 * main:测试方法 . <br/>
	 *
	 * @author Mr.Lee
	 * @param args 方法参数列表
	 */ 
	public static void main(String[] args) {
		System.out.println("本机的ip=" + PublicUtil.getIp());
	}
}