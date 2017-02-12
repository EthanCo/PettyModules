package com.ethanco.mvvmsample.model;

import com.ethanco.mvvmsample.model.bean.User;

import rx.Observable;

/**
 * Created by EthanCo on 2017/2/11.
 */

public interface IServer {
    Observable<Boolean> login(User user);
}
