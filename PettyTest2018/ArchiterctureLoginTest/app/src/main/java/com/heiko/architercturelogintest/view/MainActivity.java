package com.heiko.architercturelogintest.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.heiko.architercturelogintest.R;
import com.heiko.architercturelogintest.bean.Result;
import com.heiko.architercturelogintest.databinding.ActivityMainBinding;
import com.heiko.architercturelogintest.viewmodel.LoginViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private LoginViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setVm(mViewModel);

        mViewModel.toastLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        mViewModel.loginLiveData.observe(this, new Observer<Result>() {
            @Override
            public void onChanged(@Nullable Result result) {
                if (result.isSuccess()) {
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "登录失败:"+result.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
