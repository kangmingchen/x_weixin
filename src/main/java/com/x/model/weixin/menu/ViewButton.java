package com.x.model.weixin.menu;


/**
 * <p>Description:view类型的菜单</p>
 * @Author Chenkangming 
 * @Date 2014-7-9
 * @version 1.0
 */
public class ViewButton extends Button {

	private static final long serialVersionUID = 6263012035807056205L;
	
	private String type;
	/**
	 * 链接地址
	 */
	private String url;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
