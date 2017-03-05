package com.ethanco.simpleframe.view;


import com.ethanco.simpleframe.bean.User;

/**
 * Created by EthanCo on 2016/4/2.
 */
public interface ILoginView extends IWaitDialogOper{

    void loginSuccess(User u);

    void loginFailed(String error);
}
