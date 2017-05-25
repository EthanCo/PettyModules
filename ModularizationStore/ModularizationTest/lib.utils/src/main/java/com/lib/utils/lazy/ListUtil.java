package com.lib.utils.lazy;

import java.util.ArrayList;
import java.util.List;

/**
 * List 工具类
 *
 * @author EthanCo
 * @since 2016/12/13
 */

public class ListUtil {

    /**
     * List 分页
     *
     * @param pageIndex 当前页码
     * @param pageSize  页数
     * @param list      所有集合
     * @return
     */
    public static <T> List<T> page(int pageIndex, int pageSize, List<T> list) {
        if (pageIndex <= 0) {
            throw new IllegalArgumentException("pageIndex must > 0,current is " + pageIndex);
        }

        List<T> result = new ArrayList<>();
        if (list != null && list.size() > 0) {
            int allCount = list.size();
            //int pageCount = (allCount + pageSize - 1) / pageSize;
            /*if (pageIndex >= pageCount) {
                pageIndex = pageCount;
            }*/
            int start = (pageIndex - 1) * pageSize;
            int end = pageIndex * pageSize;
            if (end >= allCount) {
                end = allCount;
            }
            for (int i = start; i < end; i++) {
                result.add(list.get(i));
            }
        }
        return (result != null && result.size() > 0) ? result : new ArrayList<T>();
    }
}
