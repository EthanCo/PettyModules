package com.heiko.mynanohttpdtest.bean.request;

import com.heiko.mynanohttpdtest.bean.bean.UserAuthBean;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2018/3/14
 */

public class ListRequest {
    /**
     * userAuth : {"userId":"","userToken":""}
     */

    private UserAuthBean userAuth;

    public UserAuthBean getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(UserAuthBean userAuth) {
        this.userAuth = userAuth;
    }

    @Override
    public String toString() {
        return "ListRequest{" +
                "userAuth=" + userAuth +
                '}';
    }
}
