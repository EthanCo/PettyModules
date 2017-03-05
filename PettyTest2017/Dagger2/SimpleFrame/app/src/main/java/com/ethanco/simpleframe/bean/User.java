package com.ethanco.simpleframe.bean;

import android.databinding.ObservableField;

import java.io.Serializable;

/**
 * Created by EthanCo on 2016/4/1.
 */
public class User implements Serializable{

//    public User(String username, String passwrod) {
//        setUsername(username);
//        setPasswrod(passwrod);
//    }
//
//    private String username;
//    private String passwrod;
//
//    @Bindable
//    public String getUsername() {
//        return username;
//    }
//
//
//    public void setUsername(String username) {
//        this.username = username;
//        //notifyPropertyChanged(BR.);
//    }
//
//    @Bindable
//    public String getPasswrod() {
//        return passwrod;
//    }
//
//    public void setPasswrod(String passwrod) {
//        this.passwrod = passwrod;
//        //notifyPropertyChanged(BR.passwrod);
//    }

    public User(String username, String pwd) {
        this.userName.set(username);
        this.password.set(pwd);
    }

    public User(String username, String pwd, String nickName) {
        this.userName.set(username);
        this.password.set(pwd);
        this.nickName.set(nickName);
    }

    public final ObservableField<String> userName = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();
    public final ObservableField<String> nickName = new ObservableField<>();
}
