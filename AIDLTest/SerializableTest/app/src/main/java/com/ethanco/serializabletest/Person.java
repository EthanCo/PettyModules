package com.ethanco.serializabletest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by EthanCo on 2016/12/17.
 */

public class Person implements Parcelable {
    public int userId;
    public String userName;
    public boolean isMale;

    //内容描述
    //几乎在所有情况下都返回0，仅当当前对象中存在文件描述符时，返回1
    @Override
    public int describeContents() {
        return 0;
    }

    //通过writeToParcel序列化
    //flags:有两种值 0和1，为1时标志当前对象需要作为返回值返回，不能立即释放资源，几乎所有情况都为0
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //将数据写入Parcel
        dest.writeInt(this.userId);
        dest.writeString(this.userName);
        dest.writeByte(this.isMale ? (byte) 1 : (byte) 0);
    }

    public Person() {
    }

    protected Person(Parcel in) {
        this.userId = in.readInt();
        this.userName = in.readString();
        this.isMale = in.readByte() != 0;
    }

    //通过CREATOR反序列化
    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            //从序列化后的对象中创建原始对象
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            //创建指定长度的原始对象数组
            return new Person[size];
        }
    };
}
