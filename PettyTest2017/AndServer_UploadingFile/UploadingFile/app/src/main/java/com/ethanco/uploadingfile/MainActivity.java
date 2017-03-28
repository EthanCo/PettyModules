package com.ethanco.uploadingfile;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Z-MainActivity";
    private static final String UPLOADING_URL = "http://192.168.39.102:8081/upload";
    //private static final MediaType MEDIA_TYPE = MediaType.parse("application/octet-stream");
    private static final MediaType MEDIA_TYPE = MediaType.parse("multipart/form-data");
    private OkHttpClient mOkHttpClient;
    private Button btn_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .retryOnConnectionFailure(true)
                .build();

        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = Environment.getExternalStorageDirectory().getPath() + "/tg.jpg";
                Log.i(TAG, "filePath:" + path);
                //upLoadFile(UPLOADING_URL, path);
                try {
                    File file = new File(path);
                    if (file.exists()) {
                        Log.i(TAG, "文件存在");
                        upload(UPLOADING_URL, file);
                    } else {
                        Log.i(TAG, "文件不存在");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void upload(String url, File file) throws IOException {
        RequestBody formBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MediaType.parse("image/*"), file))
                .addFormDataPart("other_field", "other_field_value")
                //.addFormDataPart("action", "upload")
                //.addFormDataPart("format", "json")
                //.addFormDataPart("filename", title + "." + imageFormat) //e.g. title.png --> imageFormat = png
                //.addFormDataPart("file", "...", RequestBody.create(MEDIA_TYPE, file))
                //.addFormDataPart("token", token)
                .build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MainActivity.this.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                MainActivity.this.onResponse(response);
            }
        });
    }

    public String upload(String imageType, String userPhone, File file) {

        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
///                .addPart(
//                        Headers.of("Content-Disposition", "form-data; name=\"file\"; filename=\"" + fileName + "\""),
//                        RequestBody.create(MEDIA_TYPE_PNG, file))
//                .addPart(
//                        Headers.of("Content-Disposition", "form-data; name=\"imagetype\""),
//                        RequestBody.create(null, imageType))
//                .addPart(
//                        Headers.of("Content-Disposition", "form-data; name=\"userphone\""),
//                        RequestBody.create(null, userPhone))
                .addFormDataPart("file", "head_image", fileBody)
                .addFormDataPart("imagetype", imageType)
                .addFormDataPart("userphone", userPhone)
                .build();

        Request request = new Request.Builder()
                .url("http://xxxxx")
                .post(requestBody)
                .build();

        Response response;
        try {
            response = mOkHttpClient.newCall(request).execute();
            String jsonString = response.body().string();
            Log.d(TAG, " upload jsonString =" + jsonString);

            if (!response.isSuccessful()) {
                throw new RuntimeException("upload error code " + response);
            } else {
                JSONObject jsonObject = new JSONObject(jsonString);
                int errorCode = jsonObject.getInt("errorCode");
                if (errorCode == 0) {
                    Log.d(TAG, " upload data =" + jsonObject.getString("data"));
                    return jsonObject.getString("data");
                } else {
                    throw new RuntimeException("upload error code " + errorCode + ",errorInfo=" + jsonObject.getString("errorInfo"));
                }
            }

        } catch (IOException e) {
            Log.d(TAG, "upload IOException ", e);
        } catch (JSONException e) {
            Log.d(TAG, "upload JSONException ", e);
        }
        return null;
    }

    /**
     * 上传文件
     *
     * @param requestUrl 接口地址
     * @param filePath   本地文件地址
     */
    public <T> void upLoadFile(String requestUrl, String filePath) {
        //创建File
        File file = new File(filePath);
        //创建RequestBody
        RequestBody body = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        //创建Request
        final Request request = new Request.Builder().url(requestUrl).post(body).build();
        final Call call = mOkHttpClient.newBuilder().writeTimeout(50, TimeUnit.SECONDS).build().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MainActivity.this.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                MainActivity.this.onResponse(response);
            }
        });
    }

    private void onFailure(IOException e) {
        Log.i(TAG, "onFailure:" + e.getMessage());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
            }
        });
        //failedCallBack("上传失败", callBack);
    }

    private void onResponse(Response response) throws IOException {
        Log.i(TAG, "onResponse:" + response.isSuccessful());
        if (response.isSuccessful()) {
            final String string = response.body().string();
            Log.e(TAG, "response ----->" + string);
            //successCallBack((T) string, callBack);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "上传成功:" + string, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            //failedCallBack("上传失败", callBack);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
