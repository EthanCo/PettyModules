package com.heiko.rokidssdptest;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testCreateSearchResponse() {
        Map<String, Object> map = SsdpFactory.createSearchResponse();
        System.out.println(map.toString());
    }

    @Test
    public void testSplit() {
        //String str = "HOST: 239.255.255.250:1900";
        String str = "MAN: \"ssdp:discover\"";
        int index = str.indexOf(":");
        String s1 = str.substring(0, index);
        if (str.length() < index + 1) {
            return;
        }
        String s2 = str.substring(index + 1);
        //if (arr == null) return;

        if (s1 != null) { //!TextUtils.isEmpty(arr[0])
            System.out.println("arr[0]--->>>" + s1);
        }
        if (s2 != null) { //!TextUtils.isEmpty(arr[1])
            String value = s2.trim();
            if (value.startsWith("\"")) {
                value = value.substring(1);
            }
            if (value.endsWith("\"")) {
                value = value.substring(0,value.length() - 1);
            }
            System.out.println("arr[1]--->>>" + value);
        }
        /*if (arr[1] != null && arr[1].length() > 1) {

        }*/
    }
}