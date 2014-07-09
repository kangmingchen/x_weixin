package com.x.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * <p>Title:DBObject</p>
 * <p>Description:序列化</p>
 * @Author Chenkangming @Date 2013-10-15
 * @version 1.0
 */
public abstract class XObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
