package com.ethanco.serializabletest;

import java.io.Serializable;

/**
 * Created by EthanCo on 2016/12/16.
 */

public class User implements Serializable {
    private static final long serialVersionUID = 1234567890;

    public int userId;
    public String userName;
    public boolean isMale;

    public User(int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }
}
