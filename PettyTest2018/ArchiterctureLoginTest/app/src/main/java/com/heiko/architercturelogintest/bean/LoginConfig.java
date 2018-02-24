package com.heiko.architercturelogintest.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * 登录 配置类
 *
 * @author EthanCo
 * @since 2018/2/24
 */
@Entity(tableName = "LoginConfig")
public class LoginConfig {
    /*@PrimaryKey(autoGenerate = true)
    private int id;*/

    @NonNull
    @PrimaryKey
    private String userName;

    @ColumnInfo(name = "rememberPwd")
    private boolean rememberPwd;

    private String password;

    private Date LastModified;

    /*public void setId(int id) {
        this.id = id;
    }*/

    /*public int getId() {
        return id;
    }*/

    public boolean isRememberPwd() {
        return rememberPwd;
    }

    public void setRememberPwd(boolean rememberPwd) {
        this.rememberPwd = rememberPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastModified() {
        return LastModified;
    }

    public void setLastModified(Date lastModified) {
        LastModified = lastModified;
    }
}
