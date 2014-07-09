package com.x.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.x.model.weixin.AccessToken;
import com.x.model.weixin.menu.Button;
import com.x.model.weixin.menu.CommonButton;
import com.x.model.weixin.menu.ComplexButton;
import com.x.model.weixin.menu.Menu;
import com.x.model.weixin.menu.ViewButton;
import com.x.utils.weixin.WeixinUtil;


/**
 * <p>Description:菜单管理器，测试</p>
 * @Author Chenkangming 
 * @Date 2014-7-9
 * @version 1.0
 */
public class MenuManagerTest {
	private static Logger log=LoggerFactory.getLogger(MenuManagerTest.class);
	
	public static void main(String[] args) {
		//第三方用户唯一凭证
		String appId="wx2cb01154aed7afa1";
		//第三方用户唯一凭证密钥
		String appSecret="6be5ed698d156bd8605ca882c67f4710";
		
		//调用接口获取access_token
		AccessToken at=WeixinUtil.getAccessToken(appId, appSecret);
		
		if(null!=at){
			int result=WeixinUtil.createMenu(getMenu(), at.getToken());
			if(0==result){
				log.info("菜单创建成功！");
			}else{
				log.error("菜单创建失败，错误代码：{}", result);
			}
		}
	}
	
	/**
	 * 组装菜单数据
	 * @return
	 */
	private static Menu getMenu(){
		CommonButton btn11=new CommonButton();
		btn11.setName("天气预报");
		btn11.setType("click");
		btn11.setKey("11");
		
		CommonButton btn12=new CommonButton();
		btn12.setName("公交查询");
		btn12.setType("click");
		btn12.setKey("12");
		
		CommonButton btn13=new CommonButton();
		btn13.setName("周边搜索");
		btn13.setType("click");
		btn13.setKey("13");
		
		CommonButton btn21= new CommonButton();
		btn21.setName("歌曲点播");
		btn21.setType("click");
		btn21.setKey("21");
		
		CommonButton btn22=new CommonButton();
		btn22.setName("经典游戏");
		btn22.setType("click");
		btn22.setKey("22");
		
		CommonButton btn31=new CommonButton();
		btn31.setName("Q友圈");
		btn31.setType("click");
		btn31.setKey("31");
		
		CommonButton btn32=new CommonButton();
		btn32.setName("幽默笑话");
		btn32.setType("click");
		btn32.setKey("33");
		
		ViewButton btn33=new ViewButton();
		btn33.setName("关于我们");
		btn33.setType("view");
		btn33.setType("http://liufeng.gotoip2.com/xiaoqrobot/help.jsp");
		
		ComplexButton mainBtn1=new ComplexButton();
		mainBtn1.setName("生活助手");
		mainBtn1.setSub_button(new CommonButton[]{btn11,btn12,btn13});
		
		ComplexButton mainBtn2=new ComplexButton();
		mainBtn2.setName("休闲驿站");
		mainBtn2.setSub_button(new CommonButton[]{btn21,btn22});
		
		ComplexButton mainBtn3=new ComplexButton();
		mainBtn3.setName("更多体验");
		mainBtn3.setSub_button(new Button[]{btn31,btn32,btn33});
		
		 /** 
         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br> 
         *  
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
         */ 
		Menu menu=new Menu();
		menu.setButton(new Button[]{mainBtn1,mainBtn2,mainBtn3});
		
		return menu;
	}
}
