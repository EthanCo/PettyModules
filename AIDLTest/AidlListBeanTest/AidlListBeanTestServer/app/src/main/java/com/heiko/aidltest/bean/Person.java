package com.heiko.aidltest.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Person
 *
 * @author EthanCo
 * @since 2018/7/18
 */
public class Person implements Parcelable {
    private int age;
    private String name;
    private Integer height;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.age);
        dest.writeString(this.name);
        dest.writeValue(this.height);
    }

    protected Person(Parcel in) {
        this.age = in.readInt();
        this.name = in.readString();
        this.height = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
