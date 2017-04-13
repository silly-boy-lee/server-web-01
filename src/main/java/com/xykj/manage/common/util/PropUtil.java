package com.cmsg.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;

import javax.ws.rs.client.Client;

import com.google.common.base.Utf8;

/**
 * ClassName: PropUtil <br/>
 * Function: 读取属性文件工具类<br/>
 * date: 2016年9月20日 下午3:26:18 <br/>
 * 
 * @author Mr.Lee
 * @version
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class PropUtil {
	/** 
	 * ROOT_PATH:系统目录
	 */ 
	public static String ROOT_PATH;
	/** 
	 * CLASSPATH_FLODER:classpath中资源文件的文件夹
	 */ 
	public static String CLASSPATH_FLODER = "/resource/";

	public static Properties res;
	/** 
	 * setROOT_PATH: 设置系统根目录. <br/>
	 *
	 * @author Mr.Lee
	 * @param rootPath 根目录 
	 */ 
	public static void setROOT_PATH(String rootPath) {
		ROOT_PATH = rootPath;
	}

	/** 
	 * getPropValue:读取Properties文件键值 . <br/>
	 *
	 * @author Mr.Lee
	 * @param fname 文件名
	 * @param key  键
	 * @return 返回key对应的value
	 */ 
	public static String getPropValue(String fname, String key) {

		Properties newprop = loadProp(fname);
		if (newprop != null && newprop.getProperty(key) != null) {
			return newprop.getProperty(key).trim();
		} else {
			return "";
		}

	}

	/** 
	 * getPropValueFromClasspath:从clapath中获取资源属性文件属性值 . <br/>
	 *
	 * @author Mr.Lee
	 * @param fname 文件名
	 * @param key 键
	 * @return 返回key对应的value
	 */ 
	public static String getPropValueFromClasspath(String fname, String key) {

		Properties newprop = loadPropFromClasspath(fname);
		if (newprop != null && newprop.getProperty(key) != null) {
			return newprop.getProperty(key).trim();
		} else {
			return "";
		}

	}

	/** 
	 * loadProp: 读取Properties文件. <br/>
	 *
	 * @author Mr.Lee
	 * @param fname Properties文件名
	 * @return Properties
	 */ 
	public static Properties loadProp(String fname) {
		try {

			Properties prop = null;
			Hashtable htmlfileHash = new Hashtable();
			Hashtable htmlfileTime = new Hashtable();
			File f = new File(getPROP_ROOT() + fname + ".properties");
			if (!f.exists()) {
				return null;
			}

			long ftime = f.lastModified();
			Long hftime = (Long) htmlfileTime.get(fname);

			if (hftime == null || hftime.longValue() != ftime) {

				prop = new Properties();
				FileInputStream fis = new FileInputStream(f);
				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(bis));

				prop.load(reader);
				reader.close();
				bis.close();
				fis.close();

				htmlfileHash.put(fname, prop);
				htmlfileTime.put(fname, new Long(ftime));
			}

			return (Properties) htmlfileHash.get(fname);

		} catch (Exception e) {
			return null;
		}
	}

	/** 
	 * loadPropFromClasspath: 从classpath中读取Properties文件. <br/>
	 *
	 * @author Mr.Lee
	 * @param fname Properties文件名
	 * @return Properties
	 */ 
	public static Properties loadPropFromClasspath(String fname) {
		try {
			if(res == null){
				res = new Properties();
			}
			InputStreamReader in = new InputStreamReader(PropUtil.class.getResourceAsStream(
					CLASSPATH_FLODER + fname + ".properties"), "UTF-8");
			res.load(in);
			in.close();
			return res;
		} catch (Exception e) {
			return null;
		}
	}

	/** 
	 * getPROP_ROOT:获取properties配置文件路径 . <br/>
	 *
	 * @author Mr.Lee
	 * @return 文件路径
	 */ 
	public static String getPROP_ROOT() {
		String path = ROOT_PATH + "WEB-INF" + File.separatorChar + "conf";
		File localFile = new File(path);
		if (localFile.exists()) {
			return path + File.separatorChar;
		}
		return null;
	}
}
