package com.ethanco.propertiestest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    private String pKey = "my_pKey";
    private FileInputStream in;
    private FileInputStream in2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Properties pps = new Properties();
        String filePath = new File(DirectionCompat.getCacheDir(this)).getPath() + File.separator + "Test.properties";
        File propertiesFile = new File(filePath);
        if (!propertiesFile.exists()) {

            try {
                propertiesFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            in = new FileInputStream(filePath);
            //从输入流中读取属性列表（键和元素对）
            pps.load(in);
            //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
            //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。

            OutputStream out = new FileOutputStream(filePath);
            pps.setProperty("key1", "value1");
            pps.setProperty("key2", "value2");
            pps.setProperty("key3", "value3");
            //以适合使用 load 方法加载到 Properties 表中的格式，
            //将此 Properties 表中的属性列表（键和元素对）写入输出流
            pps.store(out, "Update " + pKey + " name");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            in2 = new FileInputStream(filePath);
            pps.load(in2);
            Enumeration enum1 = pps.propertyNames();//得到配置文件的名字
            while (enum1.hasMoreElements()) {
                String strKey = (String) enum1.nextElement();
                String strValue = pps.getProperty(strKey);
                System.out.println(strKey + "=" + strValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
