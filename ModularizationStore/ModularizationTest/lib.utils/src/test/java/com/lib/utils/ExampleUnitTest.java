package com.lib.utils;

import com.lib.utils.lazy.ListUtil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void listPageTest() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(String.valueOf(i));
        }

        assertEquals(50, list.size());

        List<String> result = ListUtil.page(1, 20, list);
        assertEquals(20, result.size());
        assertEquals(String.valueOf(0), result.get(0));
        assertEquals(String.valueOf(19), result.get(result.size() - 1));

        result = ListUtil.page(2, 20, list);
        assertEquals(20, result.size());
        assertEquals(String.valueOf(20), result.get(0));
        assertEquals(String.valueOf(39), result.get(result.size() - 1));

        result = ListUtil.page(3, 20, list);
        assertEquals(10, result.size());
        assertEquals(String.valueOf(40), result.get(0));
        assertEquals(String.valueOf(49), result.get(result.size() - 1));

        result = ListUtil.page(4, 20, list);
        assertEquals(0, result.size());

        result = ListUtil.page(6, 20, list);
        assertEquals(0, result.size());
    }
}