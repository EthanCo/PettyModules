package com.ethanco.mymaterialdialogtest.thirdparty;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ethanco.mymaterialdialogtest.R;

import java.util.ArrayList;

/**
 * 第三方Material Dialog
 */
public class ThirdPartyMaterialDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Object> list;
    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        initArray();

        findViewById(R.id.btnNormal).setOnClickListener(this);
        findViewById(R.id.btnInput).setOnClickListener(this);
        findViewById(R.id.btnWait).setOnClickListener(this);
        findViewById(R.id.btnSingleChoice).setOnClickListener(this);
        findViewById(R.id.btnMultiChoice).setOnClickListener(this);
        findViewById(R.id.btnPercent).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNormal: //普通
                new MaterialDialog.Builder(this)
                        .title("Title")
                        .content("Content")
                        .positiveText("确定")
                        .negativeText("取消")
                        .show();
                break;
            case R.id.btnInput: //输入框
                new MaterialDialog.Builder(this)
                        .title("请输入")
                        .content("写点什么东西吧")
                        //.inputType(InputType.TYPE_CLASS_TEXT) //默认，可以不加
                        //InputType.TYPE_TEXT_VARIATION_PASSWORD 密码框
                        .input("hint", "预先装载的",false, new MaterialDialog.InputCallback() { //false: allowEmptyInput 输入框为空时是否能点击确定，默认为true
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                Toast.makeText(getApplication(), input, Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                break;
            case R.id.btnWait: //等待对话框
                LoadingDialog.show(this);
                break;
            case R.id.btnSingleChoice: //单选
                new MaterialDialog.Builder(this)
                        .title("单选对话框")
                        .items(list)
                        /* 没有圆点 radiobutton
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {

                            }
                        })*/
                        //有圆点 radiobutton
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                Toast.makeText(getApplication(), "which-" + which + ":" + text, Toast.LENGTH_SHORT).show();
                                return true;
                            }
                        })
                        .alwaysCallSingleChoiceCallback()//设置该选项每次点击都会触发itemsCallbackSingleChoice
                        .positiveText("确定")
                        .show();
                break;
            case R.id.btnMultiChoice: //多选
                new MaterialDialog.Builder(this)
                        .title("多选对话框")
                        .items(list)
                        .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                                Toast.makeText(getApplication(), "选择了" + which.length + "项", Toast.LENGTH_SHORT).show();
                                return true;
                            }
                        })//.alwaysCallMultiChoiceCallback()//设置该选项每次点击都会触发itemsCallbackMultiChoice
                        .positiveText("确定")
                        .show();
                break;
            case R.id.btnPercent:
                progress = 0;
                final MaterialDialog progressDialog = new MaterialDialog.Builder(this)
                        .title("进度条对话框")
                        .progress(false, 100)
                        .show();

                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        while (progress < 100) {
                            SystemClock.sleep(100);
                            progress++;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.setProgress(progress);
                                }
                            });
                        }
                        progressDialog.dismiss();
                    }
                }.start();
                break;
            default:
        }
    }

    private void initArray() {
        list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add("Item" + i);
        }
    }
}
