package com.cmsg.util;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/** 
 * ClassName: BeanFactory <br/> 
 * Function: 工厂类 <br/> 
 * date: 2016年9月20日 下午5:12:57 <br/> 
 * 
 * @author Mr.Lee 
 * @version  
 */ 
public class BeanFactory {
	/** 
	 * wac:
	 */ 
	protected static ApplicationContext wac;
	/** 
	 * me:
	 */ 
	private static BeanFactory me;

	/** 
	 * Creates a new instance of BeanFactory. 
	 * @param wac 
	 */
	private BeanFactory(ApplicationContext wac) {
		BeanFactory.wac = wac;
	}

	/** 
	 * init: . <br/>
	 *
	 * @author Mr.Lee
	 * @param servletContext 
	 */ 
	public static void init(ServletContext servletContext) {
		if (me == null) {
			me = new BeanFactory(
					WebApplicationContextUtils
							.getRequiredWebApplicationContext(servletContext));
		}
	}

	/** 
	 * init: . <br/>
	 *
	 * @author Mr.Lee
	 * @param ctx 
	 */ 
	public static void init(ApplicationContext ctx) {
		if (me == null) {
			me = new BeanFactory(ctx);
		}
	}

	/** 
	 * getBean: 获取String Bean对象. <br/>
	 *
	 * @author Mr.Lee
	 * @param beanName java类名称
	 * @return javabena
	 */ 
	public static Object getBean(String beanName) {
		return wac.getBean(beanName);
	}

	/** 
	 * getBean:获取String Bean对象 . <br/>
	 *
	 * @author Mr.Lee
	 * @param clazz 泛型类名
	 * @param <T> 泛型
	 * @return javabena
	 */ 
	public static <T> T getBean(Class<T> clazz) {
		return wac.getBean(clazz);
	}
}
