package com.x.service;

import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.x.model.weixin.message.resp.TextMessage;
import com.x.utils.SpringContextHolder;
import com.x.utils.StringUtil;
import com.x.utils.weixin.MessageUtil;

/**
 * @ClassName: CoreService.java
 * @Description: TODO
 * 
 * @author Kenmy
 * @version V1.0
 * @Date 2014-1-11下午02:41:36
 */
public class CoreService {

	private static Logger log = Logger.getLogger(CoreService.class);

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return: String
	 * @author: Kenmy
	 * @Date: 2014-1-11 下午02:18:14
	 */
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "您好，您问的问题我还在学习中/:P-(";
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.paraseXml(request);

			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// 指令消息内容
				String order = StringUtil.ToDBC(requestMap.get("Content"));
				
				if (order.equals("?")) { // 获取住菜单帮助信息
//					respContent = WeixinOrderGroupInit.menu;
				} else if (isQqFace(order)) {
					// 判断用户发送的是否是单个QQ表情，是则返回该表情
					respContent = order;
				} else {
//					String type = WeixinOrderGroupInit.get(order);
//					if (type.equals("text")) {
//						respContent = textService.getContext(StringUtil.toInt(order)).getContent();
//					} else if (type.equals("news")) {
//						return new NewsCoreService().processRequest(requestMap);
//					}
				}
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件推送
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
//					respContent = "感谢您对勤快柳儿的喜爱\n".concat(WeixinOrderGroupInit.menu);
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// 取消订阅
					// TODO: 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO: 自定义菜单权没有开放，暂不处理该类信息
				}
			}
			respMessage=messageText(requestMap, respContent);
		} catch (Exception e) {
			log.error(e);
		}
		return respMessage;
	}

	/**
	 * 回复文本消息
	 * 
	 * @param requestMap
	 * @return
	 */
	public String messageText(Map<String, String> requestMap, String respContent) {
		// 发送帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");

		// 回复文本消息
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);
		textMessage.setContent(respContent);
		return MessageUtil.textMessageToXml(textMessage);
	}

	/**
	 * 判断是否是QQ表情
	 * 
	 * @param content
	 * @return boolean
	 */
	public static boolean isQqFace(String content) {
		boolean result = false;
		String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
		Pattern p = Pattern.compile(qqfaceRegex);
		Matcher m = p.matcher(content);
		if (m.matches()) {
			result = true;
		}
		return result;
	}

}
