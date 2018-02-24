package com.heiko.architercturelogintest.model;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Room 类型转换
 *
 * @author EthanCo
 * @since 2018/2/24
 */
public class RoomConverters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
