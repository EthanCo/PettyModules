package com.heiko.navigationtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import androidx.navigation.fragment.NavHostFragment;

//详见 https://blog.csdn.net/bug_bug_chen/article/details/80541101
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //重写onSupportNavigateUp（）方法，目的是将back事件委托出去。若栈中有两个以上Fragment，点击back键就会返回到上一个Fragment
    @Override
    public boolean onSupportNavigateUp() {
        //return super.onSupportNavigateUp();
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
        return NavHostFragment.findNavController(fragment).navigateUp();
    }
}
