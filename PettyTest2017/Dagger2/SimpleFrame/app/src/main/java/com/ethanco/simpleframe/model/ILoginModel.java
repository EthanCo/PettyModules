package com.ethanco.simpleframe.model;


import com.ethanco.simpleframe.bean.OperResult;
import com.ethanco.simpleframe.bean.User;

import rx.Observable;

/**
 * Created by EthanCo on 2016/4/1.
 */
public interface ILoginModel {
    Observable<OperResult<User>> login(User user);
}
