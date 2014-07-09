package com.x.model.weixin.menu;


/**
 * <p>Description:普通按钮（子按钮）</p>
 * @Author Chenkangming 
 * @Date 2014-7-9
 * @version 1.0
 */
public class CommonButton extends Button {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9084094083641697317L;
	private String type;
	private String key;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
