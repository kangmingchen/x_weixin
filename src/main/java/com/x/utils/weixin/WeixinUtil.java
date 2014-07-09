package com.x.utils.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.x.model.weixin.AccessToken;
import com.x.model.weixin.menu.Menu;

/**
 * @ClassName: WeixinUtil.java
 * @Description: 公众平台通用接口工具类
 * 
 * @author Kenmy
 * @version V1.0
 * @Date 2014-1-11下午04:24:39
 */

public class WeixinUtil {
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
	
	/**
	 * 菜单创建（POST） 限100（次/天）
	 */
	public static String menu_create_url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	/**
	 * 获取access_token的接口地址（GET） 限200（次/天）
	 */
	public final static String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	
	public static int createMenu(Menu menu,String accessToken){
		int result=0;
		//拼装创建菜单的url
		String url=menu_create_url.replace("ACCESS_TOKEN", accessToken);
		//将菜单对象转换成JSON字符串
		String jsonMenu=JSONObject.toJSONString(menu);
		//调用接口创建菜单
		JSONObject jsonObject=httpRequest(url, "POST", jsonMenu);
		if(null!=jsonObject){
			if(0!=jsonObject.getIntValue("errcode")){
				result=jsonObject.getIntValue("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}",jsonObject.getInteger("errcode"),jsonObject.get("errmsg"));
			}
		}
		return result;
	}
	
	/**
	 * 获取access_token
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid,String appsecret){
		AccessToken accessToken=null;
		String requestUrl=ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject=httpRequest(requestUrl, "GET", null);
		if(null!=jsonObject){
			try{
			accessToken=new AccessToken();
			accessToken.setToken(jsonObject.getString("access_token"));
			accessToken.setExpiresIn(jsonObject.getIntValue("expires_in"));
			}catch (Exception e) {
				accessToken=null;
				log.error("获取token失败 errcode:{} errmsg:{}",jsonObject.getInteger("errcode"),jsonObject.get("errmsg"));
			}
		}
		return accessToken;
	}
	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET/POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		OutputStream op = null;
		InputStream in = null;
		InputStreamReader reader = null;
		BufferedReader bufferReader = null;
		HttpsURLConnection conn = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext 对象中得到SSLSocketFactory 对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod)) {
				conn.connect();
			}

			// 当有数据需要提交时
			if (StringUtils.isNotEmpty(outputStr)) {
				op = conn.getOutputStream();
				// 注意编码格式，防止中文乱码
				op.write(outputStr.getBytes("UTF-8"));
				op.close();
				op = null;
			}

			// 将返回的输入流转换成字符串
			in = conn.getInputStream();
			reader = new InputStreamReader(in, "UTF-8");
			bufferReader = new BufferedReader(reader);

			String str = null;
			while ((str = bufferReader.readLine()) != null) {
				buffer.append(str);
			}

			jsonObject = JSONObject.parseObject(buffer.toString());
		} catch (Exception e) {
			log.error("发起请求失败",e);
		} finally {
			try {
				bufferReader.close();
				reader.close();
				in.close();
			} catch (IOException e) {
				log.error("关闭流失败",e);
			}
			buffer = null;
			reader = null;
			in = null;
			conn.disconnect();
		}
		return jsonObject;
	}
}
