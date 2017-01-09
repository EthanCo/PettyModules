package com.ethanco.mimaclient;

import org.apache.mina.core.session.IoSession;

/**
 * Created by EthanCo on 2016/12/5.
 */

/**
 *
 */
public class SessionManager {

    /**
     * 最终与服务器进行通信的对象
     */
    private IoSession mSession;

    private SessionManager() {
    }

    private static class SingleTonHolder {
        private static SessionManager sInstance = new SessionManager();
    }

    public static SessionManager getInstance() {
        return SingleTonHolder.sInstance;
    }

    public void setSession(IoSession session) {
        this.mSession = session;
    }

    public void writeToServer(Object msg) {
        if (mSession != null) {
            mSession.write(msg);
        }
    }

    public void closeSession() {
        if (mSession != null) {
            mSession.closeOnFlush();
        }
    }

    public void removeSession() {
        this.mSession = null;
    }
}
