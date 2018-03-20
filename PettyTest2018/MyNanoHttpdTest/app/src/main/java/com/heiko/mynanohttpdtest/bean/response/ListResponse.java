package com.heiko.mynanohttpdtest.bean.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2018/3/15
 */

public class ListResponse {
    /**
     * data : [{"deviceId":"330225","name":"HOPE A7","state":{"media_control":"pause"},"actions":{"media_control":["play_media","pause","play","stop","playmode","previous","next","get_meta"]},"type":"speaker"}]
     * status : 0
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * deviceId : 330225
         * name : HOPE A7
         * state : {"media_control":"pause"}
         * actions : {"media_control":["play_media","pause","play","stop","playmode","previous","next","get_meta"]}
         * type : speaker
         */

        private String deviceId;
        private String name;
        //private StateBean state;
        private Map<String, Object> state;
        //private ActionsBean actions;
        private Map<String, Object> actions;
        private String type;

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        /*public StateBean getState() {
            return state;
        }

        public void setState(StateBean state) {
            this.state = state;
        }*/

        public Map<String, Object> getState() {
            return state;
        }

        public void putState(String key, Object value) {
            if (state == null) {
                state = new HashMap<>();
            }
            state.put(key, value);
        }

        public void setState(Map<String, Object> state) {
            this.state = state;
        }

        /*public ActionsBean getActions() {
            return actions;
        }

        public void setActions(ActionsBean actions) {
            this.actions = actions;
        }*/

        public Map<String, Object> getActions() {
            return actions;
        }

        public void putAction(String key,Object value){
            if (actions == null) {
                actions = new HashMap<>();
            }
            actions.put(key, value);
        }

        public void setActions(Map<String, Object> actions) {
            this.actions = actions;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public static class StateBean {
            /**
             * media_control : pause
             */

            private String media_control;

            public String getMedia_control() {
                return media_control;
            }

            public void setMedia_control(String media_control) {
                this.media_control = media_control;
            }
        }

        public static class ActionsBean {
            private List<String> media_control;

            public List<String> getMedia_control() {
                return media_control;
            }

            public void setMedia_control(List<String> media_control) {
                this.media_control = media_control;
            }
        }
    }
    /**
     * status : 0
     * data : [{"type":"light","deviceId":"123123","name":"灯灯灯灯","actions":{"switch":["on","off"]},"state":{"switch":"off"}}]
     *//*

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        *//**
     * type : light
     * deviceId : 123123
     * name : 灯灯灯灯
     * actions : {"switch":["on","off"]}
     * state : {"switch":"off"}
     *//*

        private String type;
        private String deviceId;
        private String name;
        private ActionsBean actions;
        private StateBean state;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ActionsBean getActions() {
            return actions;
        }

        public void setActions(ActionsBean actions) {
            this.actions = actions;
        }

        public StateBean getState() {
            return state;
        }

        public void setState(StateBean state) {
            this.state = state;
        }

        public static class ActionsBean {
            @SerializedName("switch")
            private List<String> switchX;

            public List<String> getSwitchX() {
                return switchX;
            }

            public void setSwitchX(List<String> switchX) {
                this.switchX = switchX;
            }
        }

        public static class StateBean {
            *//**
     * switch : off
     *//*

            @SerializedName("switch")
            private String switchX;

            public String getSwitchX() {
                return switchX;
            }

            public void setSwitchX(String switchX) {
                this.switchX = switchX;
            }
        }
    }*/


}
