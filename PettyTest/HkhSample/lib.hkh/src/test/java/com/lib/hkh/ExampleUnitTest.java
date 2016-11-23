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
        String appSecret = "1a92aeaef8c3fbb96dd0e78a1a19067b"; //TODO 替换为真实的appSecret
        String appSecretStr = prefix + appSecret;
        String str = AES.Encrypt(appSecretStr, Hkh.AES_KEY);
        System.out.println(str);
        String newAppSecret = AES.Decrypt(str, Hkh.AES_KEY);
        System.out.println(newAppSecret);
        assertEquals(newAppSecret, appSecretStr);
    }
}