package com.xykj.manage.common.util;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/** 
 * ClassName: BeanFactory <br/> 
 * Function: java bean工厂类 <br/>
 * date: 2016年9月20日 下午5:12:57 <br/> 
 * 
 * @author Mr.Lee 
 * @version  
 */
@SuppressWarnings("unused")
public class BeanFactory {
	/** 
	 * appCtx:spring容器
	 */ 
	protected static ApplicationContext appCtx;

	/** 
	 * beanFactory: java bean 工厂类实例
	 */ 
	private static BeanFactory beanFactory;

	/**
	 * @MethodName: BeanFactory
	 * @description: 创建BeanFactory对象实例
	 * @modifyDate: 2017/4/14 + 下午1:30
	 * @author: mr.lee
	 * @param appCtx spring容器
	 */
	private BeanFactory(ApplicationContext appCtx) {
		BeanFactory.appCtx = appCtx;
	}

	/**
	 * @MethodName: init
	 * @description: 初始化BeanFactory
	 * @modifyDate: 2017/4/14 + 下午1:31
	 * @author: mr.lee
	 * @param servletContext
	 */
	public static void init(ServletContext servletContext) {
		if (beanFactory == null) {
			beanFactory = new BeanFactory(WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext));
		}
	}

	/**
	 * @MethodName: init
	 * @description: 初始化BeanFactory
	 * @modifyDate: 2017/4/14 + 下午1:34
	 * @author: mr.lee
	 * @param ctx
	 */
	public static void init(ApplicationContext ctx) {
		if (beanFactory == null) {
			beanFactory = new BeanFactory(ctx);
		}
	}

	/**
	 * @MethodName:  getBean
	 * @description: 返回beanName的类实例（已在spring窗口中注册过）
	 * @modifyDate: 2017/4/14 + 下午1:35
	 * @author: mr.lee
	 * @param beanName java类名称
	 */
	public static Object getBean(String beanName) {
		return appCtx.getBean(beanName);
	}

	/**
	 * @MethodName:  getBean
	 * @description: 返回clazz类型的类实例对象
	 * @modifyDate: 2017/4/14 + 下午1:37
	 * @author: mr.lee
	 * @param clazz Class类型
	 */
	public static <T> T getBean(Class<T> clazz) {
		return appCtx.getBean(clazz);
	}
}
