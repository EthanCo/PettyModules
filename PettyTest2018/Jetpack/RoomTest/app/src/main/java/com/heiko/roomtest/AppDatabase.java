package com.heiko.roomtest;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

//定义AppDatabase类，需要是abstract的，继承RoomDatabase
//使用Database注解，定义entities类, entities参数是一个class[]，version是数据库的版本号
@Database(entities = {User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static final String DB_NAME = "room_demo";
    private static volatile AppDatabase sInstance;

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("");执行sql语句
            database.execSQL("ALTER TABLE User ADD COLUMN lastName TEXT");
        }
    };

    //通常定义一个单例持有AppDatabase引用
    //DB_NAME是数据库文件名称
    public static AppDatabase getInstance(Application app) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(app, AppDatabase.class, DB_NAME)
                            .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }
        return sInstance;
    }
}
