package com.x.model.weixin.menu;

import com.x.model.XObject;


/**
 * <p>Description:菜单</p>
 * @Author Chenkangming 
 * @Date 2014-7-9
 * @version 1.0
 */
public class Menu extends XObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5857096793253251514L;
	
	private Button[] button;

	public Button[] getButton() {
		return button;
	}

	public void setButton(Button[] button) {
		this.button = button;
	}
	
	
}
