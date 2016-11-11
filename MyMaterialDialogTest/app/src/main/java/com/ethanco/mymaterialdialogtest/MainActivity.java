package com.ethanco.mymaterialdialogtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
    }
}
