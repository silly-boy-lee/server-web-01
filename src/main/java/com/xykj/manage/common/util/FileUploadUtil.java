package com.cmsg.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

/** 
 * ClassName: FileUploadUtil <br/> 
 * Function: 文件上传工具类 <br/> 
 * date: 2016年11月8日 上午10:15:58 <br/> 
 * 
 * @author Mr.Lee 
 * @version  
 */ 
public class FileUploadUtil {

	/** 
	 * FileUpload:上传文件 . <br/>
	 *
	 * @author Mr.Lee
	 * @param file 文件对象
	 * @param filePath 文件路径
	 * @param fileName 文件名
	 * @return 返回完整的文件上传路径名
	 */ 
	public static String FileUpload(MultipartFile file,String filePath,String fileName){
		//扩展名格式
		String extName = "";
		try {
			//取上传文件原始文件名
			String originalFileName = file.getOriginalFilename();
			//取文件扩展名的起始索引值
			int len = originalFileName.lastIndexOf(".");
			if (len >= 0) {
				extName = originalFileName.substring(len);
				copyInputStreamToFile(file.getInputStream(), filePath, fileName + extName).replaceAll("-", "");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		
		return fileName + extName;
	}
	
	/** 
	 * copyInputStreamToFile: 将所上传的文件以输入流形式存放到指定文件中. <br/>
	 *
	 * @author Mr.Lee
	 * @param in 文件输入流
	 * @param dir 上传文件路径
	 * @param realName 文件在服务器上的路径
	 * @return 返回上传的文件名
	 * @throws IOException 
	 */ 
	private static String copyInputStreamToFile(InputStream in,String dir,String realName)throws IOException{
		File file = makeFileDir(dir, realName);
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}
	
	/** 
	 * makeFileDir: 在指定路径下创建文件 . <br/>
	 *
	 * @author Mr.Lee
	 * @param dir 文件路径 
	 * @param fileName 文件名
	 * @return File
	 * @throws IOException 
	 */ 
	public static File makeFileDir(String dir,String fileName) throws IOException{
		File file = new File(dir, fileName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		return file;
	}
}
