package com.ethanco.aroutertest.testdegrade;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;

/**
 * 自定义全局降级策略
 *
 * @author EthanCo
 * @since 2017/2/20
 */

// 实现DegradeService接口，并加上一个Path内容任意的注解即可
@Route(path = "/bbb/bbb") // 必须标明注解
public class DegradeServiceImpl implements DegradeService {

    private Context mContext;

    /**
     * Router has lost.
     *
     * @param postcard meta
     */
    @Override
    public void onLost(Context context, Postcard postcard) {
        // do something.
        Toast.makeText(mContext, "lost:" + postcard.getPath(), Toast.LENGTH_SHORT).show();
    }

    /**
     * Do your init work in this method, it well be call when processor has been load.
     *
     * @param context ctx
     */
    @Override
    public void init(Context context) {
        mContext = context;
    }
}
