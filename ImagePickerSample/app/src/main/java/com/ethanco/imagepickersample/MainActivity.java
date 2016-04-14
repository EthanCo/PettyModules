package com.ethanco.imagepickersample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ethanco.imagepicker.ImagePicker;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final int IMG_REQUEST_CODE = 12434;
    private ImagePicker imagePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //需添加 READ_EXTERNAL_STORAGE 权限 和 在Application中初始化Fresco
        imagePicker = (ImagePicker) findViewById(R.id.imagePicker);
        imagePicker.setUploadButtonClickListener(new ImagePicker.UploadButtonClickListener() {
            @Override
            public void OnUploadButtonClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, IMG_REQUEST_CODE);
            }
        });
        imagePicker.setDisPlayListener(new ImagePicker.DisPlayListener() {
            @Override
            public void disPlayImage(Uri uri, Object imgUpload) {
//                SimpleDraweeView img = (SimpleDraweeView) imgUpload;
//                img.setImageURI(uri);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMG_REQUEST_CODE) {
            final Uri uri = data.getData();
            final String path = uri.toString().replace("file:///", "");
            Log.i("Z-CommentActivity", "onActivityResult path: " + path);
            final File file = new File(path);
            Log.i("Z-CommentActivity", "onActivityResult file.exsit: " + file.exists());
            if (file.exists()) {
                imagePicker.addData(uri);
            } else {
                Toast.makeText(getApplication(), "图片不存在", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
