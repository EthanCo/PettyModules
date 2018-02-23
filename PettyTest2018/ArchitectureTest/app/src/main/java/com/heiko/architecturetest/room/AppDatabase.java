package com.heiko.architecturetest.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.heiko.architecturetest.lifecycle.User;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2018/2/23
 */
//这里@Database携带的entities和version分别说明了数据表和数据库版本，userModel就是User表的操作类。
@Database(entities = {User.class}, version = 1,exportSchema = false) //没有exportSchema运行会有警告
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract UserDao userModal();

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    //inMemoryDatabaseBuilder 这种方式只是保存在内存中，并不会保存到本地数据库
                    /*Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();*/

                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user.db")
                            .allowMainThreadQueries() //为了简化代码线，请允许主线程上的查询。不要在一个真正的应用程序上这样做！关于一个示例，请参阅坚持不懈的示例。
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    int authIncrement() {
        int maxId = userModal().findMaxId();
        return maxId + 1;
    }
}
