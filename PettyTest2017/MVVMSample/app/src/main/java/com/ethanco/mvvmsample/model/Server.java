package com.ethanco.mvvmsample.model;

import android.text.TextUtils;

import com.ethanco.mvvmsample.model.bean.User;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by EthanCo on 2017/2/11.
 */

public class Server implements IServer {

    @Override
    public Observable<Boolean> login(User _user) {
        return Observable.just(_user)
                .delay(2, TimeUnit.SECONDS)
                .map(user -> verifyUser(user))
                .subscribeOn(Schedulers.io());
    }

    private Boolean checkNotNull(User user) {
        return user != null &&
                !TextUtils.isEmpty(user.getAccount()) &&
                !TextUtils.isEmpty(user.getPassword());
    }

    private boolean verifyUser(User user) {
        return checkNotNull(user) &&
                "EthanCo".equals(user.getAccount()) &&
                "123456".equals(user.getPassword());
    }
}
