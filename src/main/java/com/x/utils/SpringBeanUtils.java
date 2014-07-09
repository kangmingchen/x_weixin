package com.x.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * <p>
 * Description:获取Spring容器中Bean实例的工具类(Java泛型方法实现)。
 * </p>
 * 
 * @Author Chenkangming
 * @Date 2014-6-27
 * @version 1.0
 */
public class SpringBeanUtils implements BeanFactoryAware {

	private static BeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		SpringBeanUtils.beanFactory = beanFactory;
	}

	/**
	 * 根据bean的名称获取相应类型的对象
	 * 
	 * @param beanName
	 *            bean的名称
	 * @return Object类型的对象
	 */
	public static Object getBean(String beanName) {
		return beanFactory.getBean(beanName);
	}

	/**
	 * 根据bean的类型获取相应类型的对象，没有使用泛型，获得结果后，需要强制转换为相应的类型
	 * 
	 * @param clazz
	 *            bean的类型，没有使用泛型
	 * @return Object类型的对象
	 */
	public static Object getBean(Class<?> clazz) {
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		Object bean = wac.getBean(clazz);
		return bean;
	}

	/**
	 * 根据bean的名称获取相应类型的对象，使用泛型，获得结果后，不需要强制转换为相应的类型
	 * 
	 * @param clazz
	 *            bean的类型，使用泛型
	 * @return T类型的对象
	 */
	public static <T> T getBean2(Class<T> clazz) {
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		T bean = wac.getBean(clazz);
		return bean;
	}

}
