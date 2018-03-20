package com.heiko.mynanohttpdtest.bean.bean;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2018/3/15
 */

public class UserAuthBean {
    /**
     * userId :
     * userToken :
     */

    private String userId;
    private String userToken;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    @Override
    public String toString() {
        return "UserAuthBean{" +
                "userId='" + userId + '\'' +
                ", userToken='" + userToken + '\'' +
                '}';
    }
}