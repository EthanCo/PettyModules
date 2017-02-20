package com.ethanco.aroutertest.testservice;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2017/2/20
 */

@Route(path = "/service/hello")
public class HelloServiceImpl implements HelloService {

    private Context mContext;

    @Override
    public void sayHello(String name) {
        Toast.makeText(mContext, "hello:" + name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void init(Context context) {
        mContext = context;
        Log.e("testService", HelloService.class.getName() + " has init.");
    }
}
