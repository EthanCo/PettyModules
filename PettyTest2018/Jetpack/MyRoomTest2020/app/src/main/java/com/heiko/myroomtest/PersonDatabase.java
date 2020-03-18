package com.heiko.myroomtest;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * 指明是需要从那个class文件中创建数据库，并必须指明版本号
 *
 * @author Heiko
 * @date 2020/3/18 0018
 */
@Database(entities = {Person.class}, version = 2)
public abstract class PersonDatabase extends RoomDatabase {
    public abstract PersonDao getPersonDao();

    private static volatile PersonDatabase sInstance;

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //database.execSQL("");执行sql语句
            database.execSQL("ALTER TABLE person ADD COLUMN lastName TEXT");
        }
    };

    //通常定义一个单例持有AppDatabase引用
    //DB_NAME是数据库文件名称
    public static PersonDatabase getInstance(Application app) {
        if (sInstance == null) {
            synchronized (PersonDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(app, PersonDatabase.class, "personDemo")
                            .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }
        return sInstance;
    }
}
