package com.x.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.x.dao.mapper.SysConfigMapper;
import com.x.model.SysConfig;
import com.x.service.SysConfigService;

/**
 * @Description: 系统参数加载项
 * 
 * @author chenkangming
 * @version V1.0
 * @Date 2014-7-11下午11:04:44
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {

	@Autowired
	private SysConfigMapper mapper;

	@Override
	public Map<String, String> getSysConfig() {
		List<SysConfig> list = mapper.list();
		Map<String, String> map = new HashMap<String, String>();
		for (SysConfig info : list) {
			map.put(info.getSysKey(), info.getSysValue());
		}
		return map;
	}

}
