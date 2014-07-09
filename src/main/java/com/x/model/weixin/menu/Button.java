package com.x.model.weixin.menu;

import com.x.model.XObject;


/**
 * <p>Description:按钮的基类</p>
 * @Author Chenkangming 
 * @Date 2014-7-9
 * @version 1.0
 */
public class Button extends XObject {

	private static final long serialVersionUID = -9027346600398602946L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
