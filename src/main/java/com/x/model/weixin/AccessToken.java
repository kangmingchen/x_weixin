package com.x.model.weixin;

import com.x.model.XObject;

/**
 * <p>
 * Description:微信通用接口凭证
 * </p>
 * 
 * @Author Chenkangming
 * @Date 2014-7-9
 * @version 1.0
 */
public class AccessToken extends XObject {

	private static final long serialVersionUID = 7998992228715551431L;

	/**
	 * 获取到的凭证
	 */
	private String token;
	/**
	 * 凭证有效时间，单位：秒
	 */
	private int expiresIn;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
