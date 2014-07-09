package com.x.model.weixin.message.resp;

import java.util.List;


/**
 * <p>Description:图文消息</p>
 * @Author Chenkangming 
 * @Date 2014-7-9
 * @version 1.0
 */
public class NewsMessage extends BaseMessage {

	private static final long serialVersionUID = -8364788011757556354L;
	/**
	 * 图文消息个数，限制为10条以内
	 */
	private int ArticleCount;
	/**
	 * 多条图文消息信息，默认第一个item为大图
	 */
	private List<Article> Articles;
	
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<Article> getArticles() {
		return Articles;
	}
	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
	
	
}
