package com.x.model.weixin;

import com.x.model.XObject;


/**
 * <p>Description:指令分组</p>
 * @Author Chenkangming 
 * @Date 2014-7-9
 * @version 1.0
 */
public class WeixinOrderGroup extends XObject {

	private static final long serialVersionUID = -2148390379403169357L;

	/**
	 * 指令
	 */
	private int id;
	
	/**
	 * 指令标题
	 */
	private String title;
	
	/**
	 * 回复消息类型：text、news
	 */
	private String type;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
