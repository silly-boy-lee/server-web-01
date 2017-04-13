package com.cmsg.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;


/** 
 * ClassName: SortUtil <br/> 
 * Function: List排序工具类 <br/> 
 * date: 2016年9月14日 下午2:08:37 <br/> 
 * 
 * @author Mr.Lee 
 * @version  
 */ 
public class SortUtil {

	/** 
	 * sort:对list进行排序(一个排序参数). <br/>
	 *
	 * @author Mr.Lee
	 * @param sortLt 需要排序的list
	 * @param param 需要排序的参数名称
	 * @param orderType 排序类型：正序-asc；倒序-desc
	 * @return 排序好的list
	 */ 
	@SuppressWarnings("unchecked")
	public static List<?> sort(List<?> sortLt,String param,String orderType){
		Comparator<?> cp = ComparableComparator.getInstance();
		if ("desc".equalsIgnoreCase(orderType)) {
			cp = ComparatorUtils.reversedComparator(cp);
		}
		ArrayList<Object> sortFields = new ArrayList<>();
		sortFields.add(new BeanComparator<>(param, cp));
		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort (sortLt, multiSort);
		
		return sortLt;
	}
	
	/** 
	 * sort: 对list进行排序(两个排序参数). <br/>
	 *
	 * @author Mr.Lee
	 * @param sortList 需要排序的list
	 * @param paramOne 需要排序的参数名称
	 * @param paramTwo   需要排序的参数名称
	 * @param orderType 排序类型：正序-asc；倒序-desc
	 * @return 排序好的list
	 */ 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List sort(List sortList, String paramOne,String paramTwo, String orderType){
		Comparator<?> mycmp1 = ComparableComparator.getInstance ();
		Comparator<?> mycmp2 = ComparableComparator.getInstance ();
		if("desc".equals(orderType)){
			mycmp1 = ComparatorUtils. reversedComparator(mycmp1); //逆序（默认为正序）
		}
		
		ArrayList<Object> sortFields = new ArrayList<Object>();
		sortFields.add( new BeanComparator(paramOne , mycmp1)); //主排序（第一排序）
		sortFields.add( new BeanComparator(paramTwo , mycmp2)); //主排序（第一排序）

		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort (sortList , multiSort);
		
		return sortList;
	}
	
	
}
