package com.ethanco.aroutertest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ethanco.aroutertest.testservice.HelloService;

/**
 * ARouter
 * http://www.hdtoy.net/a/shenghuo/20170110/43964.html
 * https://github.com/alibaba/ARouter/blob/master/README_CN.md
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE = 666;
    private ARouter router = ARouter.getInstance();
    private static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;

        setContentView(R.layout.activity_main);
        initClickListener();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_test1:
                router.build("/test/activity1")
                        .navigation();
                break;
            case R.id.btn_test2:
                router.build("/test/activity2")
                        .withString("key1", "EthanCo")
                        .navigation();
                break;
            case R.id.btn_test3:
                router.build("/test/activity3")
                        .navigation(this, REQUEST_CODE);
                break;
            case R.id.btn_url:
                router.build("/test/web_view")
                        .withString("url", "file:///android_asset/schame-test.html")
                        .navigation();
                break;
            case R.id.btn_module1:
                router.build("/module/1")
                        .navigation();
                break;
            case R.id.btn_by_name:
                HelloService service = ((HelloService) router.build("/service/hello").navigation());
                service.sayHello("EthanCo");
                break;
            case R.id.btn_by_type:
                //推荐使用ByName方式获取Service，ByType这种方式写起来比较方便，
                //但如果存在多实现的情况时，SDK不保证能获取到你想要的实现
                router.navigation(HelloService.class)
                        .sayHello("EthanCo");
                break;
            case R.id.btn_interceptor:
                router.build("/test/activity4")
                        .navigation();
                break;
            case R.id.btn_degrade_local:
                ARouter.getInstance().build("/xxx/xxx").navigation(this, new NavigationCallback() {
                    @Override
                    public void onFound(Postcard postcard) {

                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        Toast.makeText(MainActivity.this, "没有找到:" + postcard.getPath(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onArrival(Postcard postcard) {

                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {

                    }
                });
                break;
            case R.id.btn_degrade_global:
                ARouter.getInstance().build("/xxx/xxx").navigation();
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (REQUEST_CODE == requestCode) {
            Toast.makeText(this, "resultCode:" + resultCode, Toast.LENGTH_SHORT).show();
        }
    }

    private void initClickListener() {
        ViewGroup layoutMain = (ViewGroup) findViewById(R.id.layout_main);
        int count = layoutMain.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = layoutMain.getChildAt(i);
            if (child.isClickable()) {
                child.setOnClickListener(this);
            }
        }
    }

    public static Activity getThis() {
        return activity;
    }
}
