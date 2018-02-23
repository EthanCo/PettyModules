package com.heiko.architecturetest.lifecycle;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2018/2/23
 */

@Entity
public class User {
    @PrimaryKey(autoGenerate = true) //autoGenerate:自动生成
    @NonNull
    public int id;
    @ColumnInfo(name = "name")
    public String name;

    public User(String name) {
        this.name = name;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
