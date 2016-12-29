package com.ethanco.propertiestest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Properties 帮助类
 *
 * @author EthanCo
 * @since 2016/12/29
 */

public class PropertiesHelper {
    private File propertiesFile;
    private Properties pps;

    public PropertiesHelper(String dir, String fileName) {
        if (!fileName.endsWith(".properties")) {
            fileName += ".properties";
        }
        File path = new File(dir);
        if (!path.exists()) {
            path.mkdirs();
        }
        this.pps = new Properties();
        String filePath = dir + File.separator + fileName;
        propertiesFile = new File(filePath);
        if (!propertiesFile.exists()) {
            try {
                propertiesFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setProperties(Map<String, String> map) {
        FileInputStream in = null;
        OutputStream out;
        try {
            in = new FileInputStream(propertiesFile.getPath());
            out = new FileOutputStream(propertiesFile.getPath());
            //从输入流中读取属性列表（键和元素对）
            pps.load(in);

            Set<Map.Entry<String, String>> entrySet = map.entrySet();
            Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> data = iterator.next();
                pps.setProperty(data.getKey(), data.getValue());
            }
            pps.store(out, "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Map<String, String> getProperties() {
        Map<String, String> map = new HashMap<>();
        FileInputStream in = null;
        try {
            in = new FileInputStream(propertiesFile.getPath());
            pps.load(in);
            Enumeration enum1 = pps.propertyNames();//得到配置文件的名字
            while (enum1.hasMoreElements()) {
                String key = (String) enum1.nextElement();
                map.put(key, pps.getProperty(key));
                System.out.println(key + "=" + pps.getProperty(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
}
