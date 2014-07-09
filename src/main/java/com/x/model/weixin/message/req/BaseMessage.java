package com.x.model.weixin.message.req;

import com.x.model.XObject;


/**
 * <p>Description:请求 消息基类（普通用户 -> 公众帐号）</p>
 * @Author Chenkangming 
 * @Date 2014-7-9
 * @version 1.0
 */
public class BaseMessage extends XObject {

	private static final long serialVersionUID = -880081566755404747L;

	/**
	 * 开发者微信
	 */
	private String ToUserName;
	/**
	 * 发送方帐号（一个OpenId）
	 */
	private String FromUserName;
	/**
	 * 消息创建时间（整型）
	 */
	private long CreateTime;
	/**
	 * 消息类型（text/image/location/link）
	 */
	private String MsgType;
	/**
	 * 消息id,64为整型
	 */
	private long MsgId;
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public long getMsgId() {
		return MsgId;
	}
	public void setMsgId(long msgId) {
		MsgId = msgId;
	}
	
	
}
