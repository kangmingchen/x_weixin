package com.x.model.weixin;

import com.x.model.XObject;

/**
 * <p>
 * Description:微信查询参数
 * </p>
 * 
 * @Author Chenkangming
 * @Date 2014-7-10
 * @version 1.0
 */
public class WeixinSearchParams extends XObject {
	private static final long serialVersionUID = 1187130588087406350L;
	/**
	 * 微信账号
	 */
	private String openId;
	/**
	 * 点击菜单ID
	 */
	private int menuId;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

}
