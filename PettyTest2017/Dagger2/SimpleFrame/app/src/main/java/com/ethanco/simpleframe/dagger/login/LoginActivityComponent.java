package com.ethanco.simpleframe.dagger.login;

import com.ethanco.simpleframe.view.LoginActivity;

import dagger.Component;

/**
 * Created by EthanCo on 2016/4/4.
 */
@Component(modules = LoginActivityModule.class)
public interface LoginActivityComponent {
    void inject(LoginActivity activity);
    //void inject(LoginViewModel loginViewModel);
}
