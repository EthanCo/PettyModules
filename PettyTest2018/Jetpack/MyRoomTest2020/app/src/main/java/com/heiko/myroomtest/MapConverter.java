package com.heiko.myroomtest;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * MsgConverter
 *
 * @author Heiko
 * @date 2020/3/19 0019
 */
public class MapConverter {
    private Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();

    @TypeConverter
    public Map<String,Integer> stringToSomeObjectList(String data) {
        if (data == null) {
            return null;
        }
        Type type = new TypeToken<Map<String, Integer>>() {}.getType();
        Map<String, Integer> map = gson.fromJson(data, type);

        return map;
    }

    @TypeConverter
    public String someObjectListToString(Map<String,Integer> someObjects) {
        return gson.toJson(someObjects);
    }
}
