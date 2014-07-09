package com.x.action.weixin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.x.utils.StringUtil;
import com.x.utils.weixin.SignUtil;

/**
 * <p>
 * Description:与微信服务器交互类
 * </p>
 * 
 * @Author Chenkangming
 * @Date 2014-7-9
 * @version 1.0
 */
@RestController
@Controller("weixin")
public class WeixinCoreAction {

	/**
	 * 确认请求来自微信服务器
	 * 
	 * @param request
	 * @return: String
	 * @author: Kenmy
	 * @Date: 2014-1-8 下午09:50:36
	 */
	@RequestMapping(value = "/coreServlet", method = RequestMethod.GET)
	public String get(HttpServletRequest request) {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		String result = null;
		// 通过校验signature对请求进行校验，若成功则原样返回echostr
		if (StringUtil.isNotEmpty(signature, timestamp, nonce)) {
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				result = echostr;
			}
		}
		return result;
	}

	/**
	 * 处理微信服务器发来的消息
	 * 
	 * @param request
	 * @return: String
	 * @author: Kenmy
	 * @Date: 2014-1-11 下午02:16:29
	 */
	@RequestMapping(value = "/coreServlet", method = RequestMethod.POST)
	public String post(HttpServletRequest request) {
		// String respMessage = new CoreService().processRequest(request);
		// return respMessage;
		return "";
	}
}
