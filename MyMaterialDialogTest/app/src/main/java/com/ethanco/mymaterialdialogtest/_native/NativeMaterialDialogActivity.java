package com.ethanco.mymaterialdialogtest._native;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.ethanco.mymaterialdialogtest.R;

import java.util.ArrayList;

/**
 * 原生Material Dialog
 */
public class NativeMaterialDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Object> list;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNormal: //普通
                MessageDialog.show(this, "Title", "Content", "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }, "取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                break;
            case R.id.btnInput: //输入框
                MessageDialog.showInput(this, "请输入", new OnPositiveButtonListener() {
                    @Override
                    public void onPositiveClick(DialogInterface dialog, int which, String text) {
                        Toast.makeText(getApplication(), text, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btnWait: //等待对话框
                LoadingDialog.show(this);
                break;
            case R.id.btnSingleChoice: //单选
                MessageDialog.showList(this, "t", list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(NativeMaterialDialogActivity.this, "", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btnMultiChoice: //多选

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
