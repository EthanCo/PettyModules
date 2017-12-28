package com.ethanco.myapplication;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.BuglyLog;
import com.tencent.bugly.crashreport.CrashReport;

public class MainActivity extends AppCompatActivity {

    private Button btnTestCrash;
    private Button btnCheckVersion;

    private RateControler rateControler = new RateControler(1000 * 100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTestCrash = (Button) findViewById(R.id.btn_test_crash);
        btnCheckVersion = (Button) findViewById(R.id.btn_check_version);

        btnTestCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrashReport.testJavaCrash();
            }
        });

        btnCheckVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 参数1：isManual 用户手动点击检查，非用户点击操作请传false
                 参数2：isSilence 是否显示弹窗等交互，[true:没有弹窗和toast] [false:有弹窗或toast]
                 */
//                new Thread() {
//                    @Override
//                    public void run() {
//                        Beta.checkUpgrade();
//                    }
//                }.start();

                BuglyLog.v("Z-Test", "test---004");
                BuglyLog.i("Z-Test", "test---005");
                CrashReport.postCatchedException(new Exception("GG MSN"));

            }
        });

        BuglyLog.v("Z-Test", "test---001");
        BuglyLog.i("Z-Test", "test---002");
        BuglyLog.e("Z-Test", "test---003");
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!rateControler.isFastDoubleClick()) {
            new Thread() {
                @Override
                public void run() {
                    SystemClock.sleep(1000 * 2);
                    Beta.checkUpgrade(false, false);
                }
            }.start();
        }
    }
}
