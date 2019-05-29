package com.heiko.roomtest;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

// 定义User表, 用Entity注解来表示这个类是一个数据库表
@Entity
public class User {
    //使用ColumnInfo注解定义一个字段名, 不用注解默认使用变量名
    @ColumnInfo(name = "first_name")
    public String firstName;

    public String lastName;

    @PrimaryKey//主键
    public int id;
}
