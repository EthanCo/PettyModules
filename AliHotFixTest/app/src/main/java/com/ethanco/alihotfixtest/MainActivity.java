package com.ethanco.alihotfixtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lib.alihotfix.AliHotFix;
import com.taobao.hotfix.HotFixManager;
import com.taobao.hotfix.PatchLoadStatusListener;
import com.taobao.hotfix.util.PatchStatusCode;

public class MainActivity extends AppCompatActivity implements PatchLoadStatusListener {
    private TextView mStatusTv;
    private String mStatusStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AliHotFix.requestExternalStoragePermission(this);
        AliHotFix.addPatchLoadStatusListener(this);
        init();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        AliHotFix.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private void init() {
        mStatusTv = (TextView) this.findViewById(R.id.tv_status);
    }

    /**
     * 更新监控台的输出信息
     *
     * @param info 更新内容
     */
    private void updateConsole(String info) {
        mStatusStr += info + "\n";
        if (mStatusTv != null) {
            mStatusTv.setText(mStatusStr);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_download:
                HotFixManager.getInstance().queryNewHotPatch();
                break;
            case R.id.btn_test:
                BaseBug.test(MainActivity.this);
                break;
            case R.id.btn_kill:
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
            case R.id.btn_clean_version:
                HotFixManager.getInstance().cleanPatches(true);
                break;
            case R.id.btn_clean_console:
                mStatusStr = "";
                updateConsole("");
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AliHotFix.removePatchLoadStatusListener(this);
    }

    @Override
    public void onload(int mode, int code, String info, int handlePatchVersion) {
        // 补丁加载回调通知
        StringBuilder sb = new StringBuilder();
        if (code == PatchStatusCode.CODE_SUCCESS_LOAD) {
            sb.append("initHotfix 表明补丁加载成功");
            // TODO: 10/24/16 表明补丁加载成功
        } else if (code == PatchStatusCode.CODE_ERROR_NEEDRESTART) {
            sb.append("initHotfix 表明新补丁生效需要重启");
            // TODO: 10/24/16 表明新补丁生效需要重启. 业务方可自行实现逻辑, 提示用户或者强制重启, 建议: 用户可以监听进入后台事件, 然后应用自杀
        } else if (code == PatchStatusCode.CODE_ERROR_INNERENGINEFAIL) {
            sb.append("initHotfix 内部引擎加载异常, 推荐此时清空本地补丁, 但是不清空本地版本号, 防止失败补丁重复加载");
            // 内部引擎加载异常, 推荐此时清空本地补丁, 但是不清空本地版本号, 防止失败补丁重复加载
            //HotFixManager.getInstance().cleanPatches(false);
        } else {
            sb.append("initHotfix 其它信息, 查看PatchStatusCode类说明");
            // TODO: 10/25/16 其它信息, 查看PatchStatusCode类说明
        }


        sb.append("Mode:").append(mode).append(" Code:").append(code).append(" Info:").append(info).append(" HandlePatchVersion:").append(handlePatchVersion);
        updateConsole(sb.toString());
    }
}
