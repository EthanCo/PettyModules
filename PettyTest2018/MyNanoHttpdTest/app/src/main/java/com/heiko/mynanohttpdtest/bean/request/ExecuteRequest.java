package com.heiko.mynanohttpdtest.bean.request;

import com.heiko.mynanohttpdtest.bean.bean.UserAuthBean;

import java.util.Map;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2018/3/15
 */

public class ExecuteRequest {
    /**
     * device : {"deviceId":"xxx","state":{"switch":"on"},"userAuth":{"userId":"","userToken":""}}
     * action : {"property":"switch","name":"on"}
     */

    private DeviceBean device;
    //private ActionBean action;
    private Map<String, Object> action;

    public DeviceBean getDevice() {
        return device;
    }

    public void setDevice(DeviceBean device) {
        this.device = device;
    }

    /*public ActionBean getAction() {
        return action;
    }

    public void setAction(ActionBean action) {
        this.action = action;
    }*/

    public Map<String, Object> getAction() {
        return action;
    }

    public void setAction(Map<String, Object> action) {
        this.action = action;
    }

    public static class DeviceBean {
        /**
         * deviceId : xxx
         * state : {"switch":"on"}
         * userAuth : {"userId":"","userToken":""}
         */

        private String deviceId;
        private Map<String,Object> state;
        private UserAuthBean userAuth;

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public Map<String, Object> getState() {
            return state;
        }

        public void setState(Map<String, Object> state) {
            this.state = state;
        }

        public UserAuthBean getUserAuth() {
            return userAuth;
        }

        public void setUserAuth(UserAuthBean userAuth) {
            this.userAuth = userAuth;
        }
    }
}
