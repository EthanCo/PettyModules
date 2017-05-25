package com.sdk.hopeplayer.socket.bean;

/**
 * 音效
 * @author hult
 *
 */
public enum SoundEffect {
	/**
	 * 经典
	 */
	Classic(0),
	/**
	 * 现代
	 */
	Modern(1),
	/**
	 * 摇滚
	 */
	Rockroll(2),
	/**
	 * 流行
	 */
	Popular(3),
	/**
	 * 舞曲
	 */
	Dance(4),
	/**
	 * 原声
	 */
	Original(5);

	private SoundEffect(int effect) {
		this.effect = effect;
	}

	public int effect;

	public static SoundEffect code(int effect) {
		switch (effect) {
		case 0:
			return Classic;
		case 1:
			return Modern;
		case 2:
			return Rockroll;
		case 3:
			return Popular;
		case 4:
			return Dance;
		case 5:
			return Original;
		default:
			return Original;
		}
	}
}
