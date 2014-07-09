package com.x.model.weixin;

import com.x.model.XObject;

/**
 * <p>
 * Description:图文消息
 * </p>
 * 
 * @Author Chenkangming
 * @Date 2014-7-9
 * @version 1.0
 */
public class WeixinMessageNews extends XObject {

	private static final long serialVersionUID = -3905654453753481018L;

	/**
	 * 自增长id
	 */
	private int id;

	/**
	 * 标题
	 */
	private String title;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 图片，支持JPS、PNG格式
	 */
	private String picUrl;
	/**
	 * 点击图文消息跳转链接
	 */
	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
