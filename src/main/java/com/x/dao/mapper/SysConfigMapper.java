package com.x.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.x.model.SysConfig;

/**
 * @Description: 加载系统参数
 * 
 * @author chenkangming
 * @version V1.0
 * @Date 2014-7-11下午10:51:56
 */
@Repository
public interface SysConfigMapper {
	/**
	 * 加载系统参数
	 * 
	 * @return
	 */
	List<SysConfig> list();
}
