package com.sdk.hopeplayer.socket.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 音源
 * @author hult
 *
 */
public enum MediaSource implements Parcelable {
	/**
	 * 未知
	 */
	Unknow(0),
	/**
	 *  随机
	 */
	Local(1),
	/**
	 * 外部
	 */
	Remote(2),
	/**
	 *  蓝牙
	 */
	Blueteeth(3);

	private MediaSource(int source) {
		this.source = source;
	}

	public int source;

	MediaSource(Parcel in) {
		source = in.readInt();
	}

	public static final Creator<MediaSource> CREATOR = new Creator<MediaSource>() {
		@Override
		public MediaSource createFromParcel(Parcel in) {
			return MediaSource.code(in.readInt());
		}

		@Override
		public MediaSource[] newArray(int size) {
			return new MediaSource[size];
		}
	};

	public static MediaSource code(int source) {
		switch (source) {
		case 1:
			return Local;
		case 2:
			return Remote;
		case 3:
			return Blueteeth;
		default:
			return Unknow;
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {

		parcel.writeInt(ordinal());
	}
}
