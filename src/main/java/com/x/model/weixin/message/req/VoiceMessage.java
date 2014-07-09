package com.x.model.weixin.message.req;

import com.x.model.XObject;


/**
 * <p>Description:音频消息</p>
 * @Author Chenkangming 
 * @Date 2014-7-9
 * @version 1.0
 */
public class VoiceMessage extends XObject {

	private static final long serialVersionUID = -3277333259381223550L;

	/**
	 * 媒体id
	 */
	private String MediaId;
	/**
	 * 语音格式
	 */
	private String Format;
	
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	
}
