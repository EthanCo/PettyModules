package com.heiko.rokidssdptest;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2018/3/16
 */

public class SsdpServer extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SsdpServer(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
