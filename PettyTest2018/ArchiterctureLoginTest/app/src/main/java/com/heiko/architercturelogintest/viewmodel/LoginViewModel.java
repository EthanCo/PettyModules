package com.heiko.architercturelogintest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.heiko.architercturelogintest.bean.LoginConfig;
import com.heiko.architercturelogintest.bean.Result;
import com.heiko.architercturelogintest.model.AppDatabase;
import com.heiko.architercturelogintest.model.LoginDao;

import java.util.Date;
import java.util.List;

/**
 * 登录观察者
 *
 * @author EthanCo
 * @since 2018/2/24
 */
public class LoginViewModel extends AndroidViewModel {
    public ObservableField<String> userName = new ObservableField<>();

    public ObservableField<String> password = new ObservableField<>();

    public ObservableBoolean rememberPwd = new ObservableBoolean();

    public MutableLiveData<String> toastLiveData = new MutableLiveData<>();

    public MutableLiveData<Result> loginLiveData = new MutableLiveData<>();

    private final LoginDao loginModel;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        loginModel = AppDatabase.get(application).loginModel();
        new Thread() {
            @Override
            public void run() {
                super.run();

                //需要在异步线程执行
                LoginConfig editConfig = loginModel.findLastEditConfig();
                if (editConfig != null) {
                    if (editConfig.isRememberPwd()) {
                        userName.set(editConfig.getUserName());
                        password.set(editConfig.getPassword());
                        rememberPwd.set(editConfig.isRememberPwd());
                    }
                }
            }
        }.start();
    }

    public void login() {
        if (checkLoginParamsFailed()) return;

        new Thread() {
            @Override
            public void run() {
                super.run();

                //需要在异步线程执行
                List<LoginConfig> loginConfigs = loginModel.findLoginConfigByName(userName.get());
                if (loginConfigs == null || loginConfigs.size() == 0) {
                    LoginConfig loginConfig = new LoginConfig();
                    loginConfig.setRememberPwd(rememberPwd.get());
                    loginConfig.setUserName(userName.get());
                    loginConfig.setPassword(password.get());
                    loginConfig.setLastModified(new Date());
                    loginModel.insertLoginConfig(loginConfig);
                } else {
                    for (LoginConfig loginConfig : loginConfigs) {
                        loginConfig.setPassword(password.get());
                        loginConfig.setLastModified(new Date());
                    }
                    loginModel.updateLoginConfigs(loginConfigs);
                }
            }
        }.start();


        boolean isLoginSuccess = isLoginSuccess();
        if (isLoginSuccess) {
            loginLiveData.postValue(new Result(isLoginSuccess));
        } else {
            loginLiveData.postValue(new Result(isLoginSuccess, "账号密码错误"));
        }
    }

    private boolean checkLoginParamsFailed() {
        if (TextUtils.isEmpty(userName.get())) {
            toastLiveData.postValue("用户名不能为空");
            return true;
        }
        if (TextUtils.isEmpty(password.get())) {
            toastLiveData.postValue("密码不能为空");
            return true;
        }
        return false;
    }

    private boolean isLoginSuccess() {
        return "zhk".equals(userName.get()) && "123456".equals(password.get());
    }

    public void onCheckRemeber(){
        Log.i("Z--", "onCheckRemeber");
        if (!rememberPwd.get()) return;

        if (!TextUtils.isEmpty(userName.get())) {
            new Thread(){
                @Override
                public void run() {
                    super.run();

                    List<LoginConfig> loginConfigs = loginModel.findLoginConfigByName(userName.get());
                    if (loginConfigs != null && loginConfigs.size() > 0) {
                        LoginConfig loginConfig = loginConfigs.get(0);
                        if (loginConfig.isRememberPwd()) {
                            userName.set(loginConfig.getUserName());
                            password.set(loginConfig.getPassword());
                            rememberPwd.set(loginConfig.isRememberPwd());
                        }
                    }
                }
            }.start();

        }
    }
}
