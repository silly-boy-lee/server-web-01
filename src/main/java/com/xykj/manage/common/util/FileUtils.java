package com.cmsg.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/** 
 * ClassName: FileUtils <br/> 
 * Function: 文件处理工具类 <br/> 
 * date: 2016年9月14日 下午3:34:48 <br/> 
 * 
 * @author Mr.Lee 
 * @version  
 */ 
public class FileUtils {
	
	/** 
	 * getFileSize: 获取文件大小 ,保留3位小数  . <br/>
	 *
	 * @author Mr.Lee
	 * @param filepath 文件所在路径及文件名组成的字符串
	 * @return 返回 KB,没有文件时返回0
	 */ 
	public static Double getFileSize(String filepath){
		File file = new File(filepath);
		return Double.valueOf(file.length())/1000.000;
	}
	
	/** 
	 * createDir:创建目录. <br/>
	 *
	 * @author Mr.Lee
	 * @param destDirName 路径字符串
	 * @return 创建成功返回true,否则返回false
	 */ 
	public static Boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		//判断有没有父路径，就是判断文件整个路径是否存在
		if(!dir.getParentFile().exists()){
			//不存在就全部创建
			return dir.getParentFile().mkdirs();		
		}
		return false;
	}
	
	/** 
	 * delFile:删除文件. <br/>
	 *
	 * @author Mr.Lee
	 * @param filePathAndName 文件所在路径及文件名组成的字符串
	 */ 
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myDelFile = new File(filePath);
			myDelFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * getContent:读取文件内容并以字节数组返回. <br/>
	 *
	 * @author Mr.Lee
	 * @param filePath 文件所在路径及文件名组成的字符串
	 * @return 字节数组,如果文件不存在，则返回0或nulls
	 * @throws IOException 
	 */ 
	@SuppressWarnings("resource")
	public static byte[] getContent(String filePath) throws IOException {
		File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("文件太大！");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length
				&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("未能读取文件：" + file.getName());
		}
		fi.close();
		return buffer;
	}
	
	/** 
	 * toByteArray:读取文件内容并以字节数组返回 . <br/>
	 *
	 * @author Mr.Lee
	 * @param filePath 文件所在路径及文件名组成的字符串
	 * @return 字节数组,如果文件不存在,抛出异常结束方法
	 * @throws IOException 
	 */ 
	public static byte[] toByteArray(String filePath) throws IOException {

		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}
	
	/** 
	 * toByteArrayTwo:读取到字节数组 . <br/>
	 *
	 * @author Mr.Lee
	 * @param filePath 文件所在路径及文件名组成的字符串
	 * @return 字节数组,如果文件不存在,抛出异常结束方法
	 * @throws IOException 
	 */ 
	public static byte[] toByteArrayTwo(String filePath) throws IOException {
		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				 System.out.println("reading");
			}
			return byteBuffer.array();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/** 
	 * toByteArrayThree: 可以在处理大文件时，提升性能. <br/>
	 *
	 * @author Mr.Lee
	 * @param filePath 文件所在路径及文件名组成的字符串
	 * @return 字节数组,如果文件不存在,抛出异常结束方法s
	 * @throws IOException 
	 */ 
	public static byte[] toByteArrayThree(String filePath) throws IOException {

		FileChannel fc = null;
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(filePath, "r");
			fc = rf.getChannel();
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0,
					fc.size()).load();
			System.out.println(byteBuffer.isLoaded());
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				rf.close();
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
