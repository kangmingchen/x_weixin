package com.x.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.x.model.weixin.WeixinMessageNews;
import com.x.model.weixin.message.resp.Article;
import com.x.model.weixin.message.resp.NewsMessage;
import com.x.model.weixin.message.resp.TextMessage;
import com.x.utils.weixin.MessageUtil;


/**
 * <p>Description:微信MessageService类</p>
 * @Author Chenkangming 
 * @Date 2014-7-10
 * @version 1.0
 */
public class WeixinMessageService {

	private Logger log = Logger.getLogger("weixin");

	public String processRequest(Map<String, String> requestMap) {
		String respMessage = null;
		try {
			 List<WeixinMessageNews> list = listDemo("123456");
//			List<WeixinMessageNews> list = service.newList(params);
//			if (list != null && list.size() != 0) { // 优先下拉图文消息，如果没有图文消息，则下拉文本消息
//				respMessage = newsMessage(requestMap, list, params);
//			} else {
//				WeixinMessageText text = service.text(params);
//				if (text != null) {
//					respMessage = textMessage(requestMap, text.getContent());
//				} else {
//					respMessage = textMessage(requestMap, "该功能正在开发中");
//				}
//			}
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
	public String textMessage(Map<String, String> requestMap, String respContent) {
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
	 * 回复图文消息
	 * 
	 * @param requestMap
	 * @param params
	 * @return
	 */
	private String newsMessage(Map<String, String> requestMap, List<WeixinMessageNews> list) {
		String respMessage;
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");
		// 接收用户发送的文本消息内容
		// String content = requestMap.get("Content");

		// 创建图文消息
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setFuncFlag(0);

		List<Article> articleList = new ArrayList<Article>();

		Article article = null;
		for (WeixinMessageNews info : list) {
			article = new Article();
			article.setTitle(info.getTitle());
			article.setDescription(info.getDescription());
			article.setPicUrl(info.getPicUrl());
			article.setUrl(info.getUrl());
			articleList.add(article);
		}
		// 设置图文消息个数
		newsMessage.setArticleCount(articleList.size());
		// 设置图文消息包含的图文集合
		newsMessage.setArticles(articleList);
		// 将图文消息对象转换成xml字符串
		respMessage = MessageUtil.newsMessageToXml(newsMessage);
		return respMessage;
	}

	/**
	 * emoji表情转换（hex -> utf-16）
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}

	/**
	 * demo数据
	 * 
	 * @return
	 */
	public List<WeixinMessageNews> listDemo(String openId) {
		List<WeixinMessageNews> list = new ArrayList<WeixinMessageNews>();
		WeixinMessageNews info = new WeixinMessageNews();
		info.setTitle("《弹弹堂》红包大抽奖");
		info.setDescription("《弹弹堂》红包大抽奖");
		info.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/icyFnNIJDKn7OY8Gkcbfcjv6qj1rQonlp5KQW08zIrFrlmmAkSibm81ntXQcjRSXSRxw9bjfL8HSXExFlCRcgyYg/0");
		info.setUrl("http://119.147.224.186/db_common/weixin/lottery?Account=" + openId + "&gameId=1");
		list.add(info);
		return list;
	}
}
