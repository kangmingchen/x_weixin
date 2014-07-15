package com.x.model;

/**
 * @Description: 系统参数配置
 * 
 * @author chenkangming
 * @version V1.0
 * @Date 2014-7-11下午10:52:58
 */
public class SysConfig extends XObject {
	private static final long serialVersionUID = 1940159048073343773L;
	
	private String sysKey;
	private String sysValue;

	public String getSysKey() {
		return sysKey;
	}

	public void setSysKey(String sysKey) {
		this.sysKey = sysKey;
	}

	public String getSysValue() {
		return sysValue;
	}

	public void setSysValue(String sysValue) {
		this.sysValue = sysValue;
	}

}
