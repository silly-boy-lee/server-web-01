package com.xykj.manage.common.util;

import java.lang.management.MemoryManagerMXBean;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
 
/**
 * @ClassName: DateUtil
 * @description: 日期格式化工具类
 * @modifyDate: 2017/4/14 + 下午2:03
 * @author: mr.lee
 */
@SuppressWarnings("unused")
public class DateUtil {
	
	/** 
	 * sdfYear:
	 */ 
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	/** 
	 * sdfDay:
	 */ 
	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	/** 
	 * sdfDays:
	 */ 
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	/** 
	 * sdfTime:
	 */ 
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/** 
	 * sdfTimes:
	 */ 
	private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");


	/**
	 * @MethodName: getSdfTimes
	 * @description: 获取yyyyMMddHHmmss格式的日期字符串
	 * @modifyDate: 2017/4/14 + 下午2:04
	 * @author: mr.lee
	 * @return 返回yyyyMMddHHmmss格式的日期字符串
	 */
	public static String getSdfTimes() {
		return sdfTimes.format(new Date());
	}

	/**
	 * @MethodName: getYear
	 * @description: 获取yyyy格式的日期字符串
	 * @modifyDate: 2017/4/14 + 下午2:05
	 * @author: mr.lee
	 * @param @return 返回yyyy格式的日期字符串
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * @MethodName: getDay
	 * @description: 获取yyyy-MM-dd格式的日期字符串
	 * @modifyDate: 2017/4/14 + 下午2:06
	 * @author: mr.lee
	 * @return 返回yyyy-MM-dd格式的日期字符串
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * @MethodName: getDays
	 * @description: 获取YYYYMMDD格式的日期字符串
	 * @modifyDate: 2017/4/14 + 下午2:07
	 * @author: mr.lee
	 * @return 返回YYYYMMDD格式的日期字符串
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * @MethodName: getTime
	 * @description: 获取YYYY-MM-DD HH:mm:ss格式的日期字符串
	 * @modifyDate: 2017/4/14 + 下午2:08
	 * @author: mr.lee
	 * @return 返回YYYY-MM-DD HH:mm:ss格式的日期字符串
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	 * @MethodName: compareDate
	 * @description: 比较两个日期字符串的大小
	 * @modifyDate: 2017/4/14 + 下午2:09
	 * @author: mr.lee
	 * @param s 起始日期
	 * @param e 结束日期
	 * @return 如果s>=e 返回true 否则返回false
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * @MethodName: fomatDate
	 * @description: 格式化日期
	 * @modifyDate: 2017/4/14 + 下午2:09
	 * @author: mr.lee
	 * @param date 日期字符串
	 * @return 返回格式化好的日期字符串
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @MethodName: isValidDate
	 * @description: 校验日期是否合法
	 * @modifyDate: 2017/4/14 + 下午2:10
	 * @author: mr.lee
	 * @param s 日期字符串
	 * @return true 合法，false 不合法
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	/**
	 * @MethodName:  getDiffYear
	 * @description: 计算两个日期时间之间的年数
	 * @modifyDate: 2017/4/14 + 下午2:11
	 * @author: mr.lee
	 * @param startTime 起始时间
	 * @param endTime 结束时间
	 * @return 返回相隔的天数
	 */
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			return 0;
		}
	}

    /**
     * @MethodName: getDaySub
     * @description: 计算两个时间之间的天数
     * @modifyDate: 2017/4/14 + 下午2:12
     * @author: mr.lee
     * @param beginDateStr 起始时间
	 * @param endDateStr 结束时间
	 * @return 返回相隔的天数
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }

    /**
     * @MethodName:  getAfterDayDate
     * @description: 得到n天之后的日期
     * @modifyDate: 2017/4/14 + 下午2:12
     * @author: mr.lee
     * @param days n天
	 * @return 返回n天之后的日期
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }

    /**
     * @MethodName: getAfterDayWeek
     * @description: 得到n天之后是周几
     * @modifyDate: 2017/4/14 + 下午2:13
     * @author: mr.lee
     * @author Mr.Lee
	 * @param days n天
	 * @return 周几
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
    	//java.util包
        Calendar canlendar = Calendar.getInstance();
        //日期减 如果不够减会将月变动
        canlendar.add(Calendar.DATE, daysInt); 
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }
}
