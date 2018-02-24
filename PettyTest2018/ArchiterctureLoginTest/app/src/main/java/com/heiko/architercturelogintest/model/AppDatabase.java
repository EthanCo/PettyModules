package com.heiko.architercturelogintest.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.heiko.architercturelogintest.bean.LoginConfig;

/**
 * @author EthanCo
 * @since 2018/2/23
 */
@Database(entities = {LoginConfig.class}, version = 1,exportSchema = false) //没有exportSchema运行会有警告
@TypeConverters({RoomConverters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract LoginDao loginModel();

    public static AppDatabase get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "test.db")
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
