package com.x.model.weixin.message.resp;


/**
 * <p>Description:文本消息</p>
 * @Author Chenkangming 
 * @Date 2014-7-9
 * @version 1.0
 */
public class TextMessage extends BaseMessage {
	private static final long serialVersionUID = -2924240065232192793L;
	
	/**
	 * 回复的消息内容
	 */
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
}
