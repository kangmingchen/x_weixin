package com.x.model.weixin.message.req;


/**
 * <p>Description:图片消息</p>
 * @Author Chenkangming 
 * @Date 2014-7-9
 * @version 1.0
 */
public class ImageMessage extends BaseMessage {

	private static final long serialVersionUID = -3000475766626949573L;

	/**
	 * 图片链接
	 */
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	
}
