package com.ethanco.simpleframe.model;


import com.ethanco.simpleframe.bean.OperResult;
import com.ethanco.simpleframe.bean.User;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by EthanCo on 2016/4/1.
 */
public class LoginModel implements ILoginModel {
    public Observable<OperResult<User>> login(User user) {
        return Observable.just(user).delay(3, TimeUnit.SECONDS) //模拟访问网络
                .map(u -> {
                    Boolean isLoginSuccess =
                            "ethanco".equals(u.userName.get()) && "123456".equals(u.password.get());
                    OperResult<User> result = new OperResult();
                    result.setSuccess(isLoginSuccess);
                    if (isLoginSuccess) {
                        user.nickName.set("可乐"); //模拟从网络获取的数据
                        result.setEntity(user);
                    } else {
                        result.setError("账号或密码错误");
                    }
                    return result;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        //
    }
}
