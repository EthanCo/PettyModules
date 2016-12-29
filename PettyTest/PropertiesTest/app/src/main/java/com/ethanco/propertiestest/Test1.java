package com.ethanco.propertiestest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2016/12/29
 */

public class Test1 {
    public static void main(String[] args) {
        Properties pps = new Properties();
        try {
            pps.load(new FileInputStream("local.properties"));
            Enumeration enum1 = pps.propertyNames();//得到配置文件的名字
            while (enum1.hasMoreElements()) {
                String strKey = (String) enum1.nextElement();
                String strValue = pps.getProperty(strKey);
                System.out.println(strKey + "=" + strValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
