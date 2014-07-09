package com.x.model.weixin.message.resp;

/**
 * <p>
 * Description:音乐消息
 * </p>
 * 
 * @Author Chenkangming
 * @Date 2014-7-9
 * @version 1.0
 */
public class MusicMessage extends BaseMessage {

	private static final long serialVersionUID = -8180120239448331946L;
	
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}

}
