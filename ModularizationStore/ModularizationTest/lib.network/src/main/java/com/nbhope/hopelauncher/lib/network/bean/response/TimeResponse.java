package com.nbhope.hopelauncher.lib.network.bean.response;

/**
 * @Description Time Response
 * Created by EthanCo on 2016/6/12.
 */
public class TimeResponse extends BaseResponse<TimeResponse.Entity> {

    public static class Entity implements BaseDataBean {
        public String getTime() {
            return Time;
        }

        public void setTime(String time) {
            Time = time;
        }

        private String Time;

        protected String Message;

        @Override
        public String getMessage() {
            return Message;
        }

        public void setMessage(String message) {
            this.Message = message;
        }
    }
}
