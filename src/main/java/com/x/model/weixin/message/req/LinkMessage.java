package com.x.model.weixin.message.req;


/**
 * <p>Description:连接信息</p>
 * @Author Chenkangming 
 * @Date 2014-7-9
 * @version 1.0
 */
public class LinkMessage extends BaseMessage {

	private static final long serialVersionUID = -4788271742764757625L;

	/**
	 * 消息标题
	 */
	private String Title;
	/**
	 * 消息描述
	 */
	private String Description;
	/**
	 * 消息链接
	 */
	private String Url;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	
}
