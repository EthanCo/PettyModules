package com.ethanco.simpleframe.dagger.login;

import com.ethanco.simpleframe.view.ILoginView;
import com.ethanco.simpleframe.viewmodel.LoginViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EthanCo on 2016/4/4.
 */
@Module
public class LoginActivityModule {

    private final ILoginView view;

    public LoginActivityModule(ILoginView view) {
        this.view = view;
    }

    @Provides
    public LoginViewModel provideLoginViewModel() {
        return new LoginViewModel(view);
    }

//    @Provides
//    public ILoginModel provideLoginModel() {
//        return new LoginModel();
//    }
//    @Provides
//    public Person generatePerson() {
//    return new Person("EthanCo", 16);
//}
}
