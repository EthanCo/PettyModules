package com.heiko.aidltest.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 9/5/16
 * Time: 5:53 PM
 * Desc: PlayList
 */
public class RemotePlayList implements Parcelable {
    private int id;
    private String name;
    private List<RemoteSong> songs = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RemoteSong> getSongs() {
        if (songs == null) {
            return new ArrayList<>();
        }
        return songs;
    }

    public void setSongs(List<RemoteSong> songs) {
        this.songs = songs;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeTypedList(this.songs);
    }

    public RemotePlayList() {
    }

    protected RemotePlayList(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.songs = in.createTypedArrayList(RemoteSong.CREATOR);
    }

    public static final Creator<RemotePlayList> CREATOR = new Creator<RemotePlayList>() {
        @Override
        public RemotePlayList createFromParcel(Parcel source) {
            return new RemotePlayList(source);
        }

        @Override
        public RemotePlayList[] newArray(int size) {
            return new RemotePlayList[size];
        }
    };

    @Override
    public String toString() {
        return "RemotePlayList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", songs=" + songs +
                '}';
    }
}
