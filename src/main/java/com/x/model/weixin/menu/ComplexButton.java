package com.x.model.weixin.menu;


/**
 * <p>Description:复杂按钮（父按钮）</p>
 * @Author Chenkangming 
 * @Date 2014-7-9
 * @version 1.0
 */
public class ComplexButton extends Button {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3347039240223987075L;
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
	
	
}
