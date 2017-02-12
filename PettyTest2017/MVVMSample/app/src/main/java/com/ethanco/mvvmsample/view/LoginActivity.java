package com.ethanco.mvvmsample.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ethanco.mvvmsample.R;
import com.ethanco.mvvmsample.databinding.ActivityLoginBinding;
import com.ethanco.mvvmsample.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLoginVM(new LoginViewModel());
    }
}
