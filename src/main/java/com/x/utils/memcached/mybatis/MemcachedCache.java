package com.x.utils.memcached.mybatis;

import java.util.LinkedList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.log4j.Logger;

import com.x.utils.memcached.MemcachedManage;

/**
 * <p>
 * Title:MemcachedCache
 * </p>
 * <p>
 * Description:Mybatis 缓存
 * </p>
 * 
 * @Author Chenkangming @Date 2013-12-27
 * @version 1.0
 */
public class MemcachedCache implements Cache {

	private static Logger log = Logger.getLogger(MemcachedCache.class);

	private LinkedList<String> cacheKeys = new LinkedList<String>();

	/** The ReadWriteLock. */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private String id;

	public MemcachedCache(String id) {
		this.id = id;
	}

	@Override
	public void clear() {
		for (int i = 0; i < cacheKeys.size(); i++) {
			String cacheKey = cacheKeys.get(i);
			try {
				MemcachedManage.delete(cacheKey);
				cacheKeys.remove(cacheKey);
			} catch (Exception e) {
				log.error(e);
			}
		}
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public Object getObject(Object key) {
		String cacheKey = String.valueOf(key.hashCode());
		Object value = null;
		try {
			value = MemcachedManage.get(cacheKey);
			if (!cacheKeys.contains(cacheKey)) {
				cacheKeys.add(cacheKey);
			}
		} catch (Exception e) {
			log.error(e);
		}
		return value;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}

	@Override
	public int getSize() {
		return cacheKeys.size();
	}

	@Override
	public void putObject(Object key, Object value) {
		String cacheKey = String.valueOf(key.hashCode());
		try {
			MemcachedManage.add(cacheKey, value);
			if (!cacheKeys.contains(cacheKey)) {
				cacheKeys.add(cacheKey);
			}
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public Object removeObject(Object key) {
		String cacheKey = String.valueOf(key.hashCode());
		try {
			MemcachedManage.delete(cacheKey);
			cacheKeys.remove(cacheKey);
		} catch (Exception e) {
			log.error(e);
		}
		return true;
	}

}
