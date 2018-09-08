package com.heiko.aidltest.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 9/2/16
 * Time: 4:01 PM
 * Desc: Song
 */
public class RemoteSong implements Parcelable {
    private int id;

    private String title;

    private int duration;

    private String fileName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public RemoteSong() {
    }

    @Override
    public String toString() {
        return "RemoteSong{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeInt(this.duration);
        dest.writeString(this.fileName);
    }

    protected RemoteSong(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.duration = in.readInt();
        this.fileName = in.readString();
    }

    public static final Creator<RemoteSong> CREATOR = new Creator<RemoteSong>() {
        @Override
        public RemoteSong createFromParcel(Parcel source) {
            return new RemoteSong(source);
        }

        @Override
        public RemoteSong[] newArray(int size) {
            return new RemoteSong[size];
        }
    };
}
