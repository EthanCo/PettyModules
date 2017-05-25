package com.lib.hkh;

import com.lib.hkh.security.AES;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void encrypt() throws Exception {
        String prefix = Hkh.PREFIX;
        String appSecret = "32725c1c1fe583ed300e3a46e9f9f73e"; //替换为真实的appSecret
        String appSecretStr = prefix + appSecret;
        String str = AES.Encrypt(appSecretStr, Hkh.AES_KEY);
        System.out.println(str);
        String newAppSecret = AES.Decrypt(str, Hkh.AES_KEY);
        System.out.println(newAppSecret);
        assertEquals(newAppSecret, appSecretStr);
    }
}