package com.ethanco.mymaterialdialogtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnNormal).setOnClickListener(this);
        findViewById(R.id.btnInput).setOnClickListener(this);
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
                        .input("hint", "预先装载的", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                Toast.makeText(getApplication(), input, Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                break;
            default:
        }
    }
}
