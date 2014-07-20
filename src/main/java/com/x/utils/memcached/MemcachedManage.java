package com.x.utils.memcached;

import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import com.x.utils.SpringContextHolder;

public class MemcachedManage {

	private static Logger log = Logger.getLogger("MemcachedManage");

	private static MemcachedClient client = (MemcachedClient) SpringContextHolder.getBean("memcachedCilent");

	public MemcachedManage() {

	}

	// 删除
	public static void delete(String key) {
		try {
			client.delete(key);
		} catch (TimeoutException e) {
			e.printStackTrace();
			log.error(e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.error(e);
		} catch (MemcachedException e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	// 清空
	public static void clear() {
		try {
			client.flushAll();
		} catch (TimeoutException e) {
			e.printStackTrace();
			log.error(e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.error(e);
		} catch (MemcachedException e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	// 获取缓存内容
	public static Object get(String key) {
		Object obj = null;
		try {
			obj = client.get(key);
		} catch (TimeoutException e) {
			e.printStackTrace();
			log.error(e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.error(e);
		} catch (MemcachedException e) {
			e.printStackTrace();
			log.error(e);
		}
		return obj;
	}

	// 设置缓存内容
	public static boolean add(String name, Object value) {
		boolean ret = false;
		try {
			ret = client.add(name, 0, value);
			if (!ret) {
				ret = client.replace(name, 0, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return ret;
	}

	/**
	 * 设置缓存内容
	 * 
	 * @param name
	 *            key值
	 * @param value
	 * @param time
	 *            缓存有效时长,以毫秒计算
	 * @return boolean
	 */
	public static boolean add(String name, Object value, long time) {
		time = time / 1000;
		boolean ret = false;
		try {
			ret = client.add(name, 0, value, time);
			if (!ret) {
				ret = client.replace(name, 0, value, time);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return ret;
	}
}
