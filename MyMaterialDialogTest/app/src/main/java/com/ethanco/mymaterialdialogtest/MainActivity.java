package com.ethanco.mymaterialdialogtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.ethanco.mymaterialdialogtest._native.NativeMaterialDialogActivity;
import com.ethanco.mymaterialdialogtest.thirdparty.ThirdPartyMaterialDialogActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_native_material_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NativeMaterialDialogActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_third_party).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThirdPartyMaterialDialogActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_application_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new Thread(){
                   @Override
                   public void run() {
                       super.run();

                       AlertDialog.Builder builder = new AlertDialog.Builder(App.getInstance());
                       builder.setCancelable(false);
                       builder.setTitle("标题");
                       builder.setMessage("消息内容");
                       builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                           public void onClick(DialogInterface dialog, int which) {
                               Toast.makeText(getApplicationContext(), "OK!", Toast.LENGTH_SHORT).show();
                           }
                       });
                       AlertDialog dialog = builder.create();
                       dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                       dialog.show();
                   }
               }.start();
            }
        });
    }
}
