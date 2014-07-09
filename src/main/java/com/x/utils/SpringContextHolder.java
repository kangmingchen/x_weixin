package com.x.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

/**
 * <p>
 * Title:SpringContextHolder
 * </p>
 * <p>
 * Description:获取Spring容器中的bean
 * </p>
 * 
 * @Author Chenkangming @Date 2013-10-14
 * @version 1.0
 */
public class SpringContextHolder extends ApplicationObjectSupport {

	private static ApplicationContext applicationContext = null;

	protected void initApplicationContext(ApplicationContext context) throws BeansException {
		super.initApplicationContext(context);
		if (SpringContextHolder.applicationContext == null) {
			SpringContextHolder.applicationContext = context;
			System.out.println("---------------------------------------------------------------------");
			System.out.println("========ApplicationContext配置成功,在普通类可以通过调用ToolSpring.getAppContext()获取applicationContext对象,applicationContext=" + applicationContext + "========");
			System.out.println("---------------------------------------------------------------------");
		}
	}

	public static ApplicationContext getAppContext() {
		return applicationContext;
	}

	public static Object getBean(String name) {
		return getAppContext().getBean(name);
	}

	/**
	 * 根据bean的名称获取相应类型的对象，使用泛型，获得结果后，不需要强制转换为相应的类型
	 * 
	 * @param clazz
	 *            bean的类型，使用泛型
	 * @return T类型的对象
	 */
	public static <T> T getBean2(Class<T> clazz) {
		T bean = getAppContext().getBean(clazz);
		return bean;
	}
}
