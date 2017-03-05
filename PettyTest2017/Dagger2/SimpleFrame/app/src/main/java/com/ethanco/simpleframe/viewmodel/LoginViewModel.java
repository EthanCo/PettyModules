package com.ethanco.simpleframe.viewmodel;


import com.ethanco.simpleframe.bean.User;
import com.ethanco.simpleframe.model.ILoginModel;
import com.ethanco.simpleframe.model.LoginModel;
import com.ethanco.simpleframe.view.ILoginView;

import javax.inject.Inject;

/**
 * Created by EthanCo on 2016/4/2.
 */
public class LoginViewModel {
    private ILoginView view;
    //@Inject
    ILoginModel model;

    @Inject
    public LoginViewModel(ILoginView view) {
        this.view =view;
        this.model = new LoginModel();
        //DaggerLoginActivityComponent.builder().build().inject(this);
    }

    public void login(User user) {
        view.showWaitDailog();
        model.login(user).subscribe(
                r -> {
                    if (r.getSuccess())
                        view.loginSuccess(r.getEntity());
                    else
                        view.loginFailed(r.getError());
                }, t -> view.loginFailed(t.toString()),
                () -> view.dismissDialog());
    }
}
