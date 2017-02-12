package com.ethanco.mvvmsample.viewmodel;

import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.ethanco.mvvmsample.model.IServer;
import com.ethanco.mvvmsample.model.Server;
import com.ethanco.mvvmsample.model.bean.User;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by EthanCo on 2017/2/11.
 */

public class LoginViewModel {
    public static final String TAG = "Z-LoginViewModel";
    private IServer server;

    public LoginViewModel() {
        this.server = new Server();
    }

    public ObservableField<String> accept = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> loginStatus = new ObservableField<>();

    public void login(View view) {
        //Context context = view.getContext();
        loginStatus.set("正在登陆...");
        Log.i(TAG, "accept:" + accept.get() + " password:" + password.get());
        server.login(new User(accept.get(), password.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(result -> loginResult(result),
                        throwable -> loginFailed(),
                        () -> {
                        });
    }

    private void loginResult(boolean isSuccess) {
        if (isSuccess) {
            loginStatus.set("登陆成功");
        } else {
            loginFailed();
        }
    }

    private void loginFailed() {
        loginStatus.set("登陆失败");
    }
}
