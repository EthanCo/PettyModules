package com.sdk.hopeplayer.musicplayer.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 9/5/16
 * Time: 5:48 PM
 * Desc: PlayMode
 */
public enum PlayMode implements Parcelable {
    SINGLE,
    LOOP,
    LIST,
    SHUFFLE, playMode;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PlayMode> CREATOR = new Creator<PlayMode>() {
        @Override
        public PlayMode createFromParcel(final Parcel source) {
            return PlayMode.valueOf(source.readString());
        }

        @Override
        public PlayMode[] newArray(final int size) {
            return new PlayMode[size];
        }
    };

    public static PlayMode getDefault() {
        return LOOP;
    }

    public static PlayMode switchNextMode(PlayMode current) {
        if (current == null) return getDefault();

        switch (current) {
            case LOOP:
//                return LIST;
//            case LIST:
                return SHUFFLE;
            case SHUFFLE:
                return SINGLE;
            case SINGLE:
                return LOOP;
        }
        return getDefault();
    }

    public int getMode(){
        switch (this){
            case SHUFFLE:
                return 1;
            case LOOP:
                return 2;
            case SINGLE:
                return 3;
            default:
                return 0;
        }
    }

    public static PlayMode setMode(int mode){
        switch (mode){
            case 1:
                return SHUFFLE;
            case 2:
                return LOOP;
            case 3:
                return SINGLE;
            default:
                return LOOP;
        }
    }

}
