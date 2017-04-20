package com.lib.utils.lazy;

import android.text.TextUtils;

import java.util.Collections;
import java.util.List;

/**
 * 避免为null
 *
 * @author EthanCo
 * @since 2017/3/6
 */

public class EmptyUtil {
    public static boolean filterNull(Boolean result) {
        if (result == null) {
            return false;
        }
        return result;
    }

    public static String filterNull(String result, String defValue) {
        if (TextUtils.isEmpty(result)) {
            return defValue;
        }
        return result;
    }

    public static String filterNull(String result) {
        return filterNull(result);
    }

    public static int filterNull(Integer result, Integer defValue) {
        if (result == null) {
            return defValue;
        }
        return result;
    }

    public static int filterNull(Integer result) {
        return filterNull(result);
    }

    public static <E> List<E> filterNull(List<E> result) {
        if (result == null) {
            return Collections.emptyList();
        }
        return result;
    }
}
