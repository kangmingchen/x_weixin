package com.x.model.weixin;

import com.x.model.XObject;


/**
 * <p>Description:回复文本信息</p>
 * @Author Chenkangming 
 * @Date 2014-7-9
 * @version 1.0
 */
public class WeixinMessageText extends XObject {

	private static final long serialVersionUID = -2019584996216861231L;

	/**
	 * 文本内容
	 */
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
