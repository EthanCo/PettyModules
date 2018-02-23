package com.heiko.architecturetest.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.heiko.architecturetest.lifecycle.User;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Android Room从入门到放弃
 * http://blog.csdn.net/u012735483/article/details/78737622
 *
 * Android Architecture Components 入门（一）—— Android Room Library 简单使用
 * http://linshen.me/blog/2017/08/03/android-room-library-tutorial/
 *
 * @author EthanCo
 * @since 2018/2/23
 */
@Dao
public interface UserDao {

    @Query("select * from user")
    List<User> loadAllUsers();

    @Query("select * from user where id = :id")
    User loadUserById(int id);

    @Query("SELECT MAX(id) FROM user")
    int findMaxId();

    @Query("select * from user where name = :name ")
    List<User> findUserByName(String name);

    @Insert(onConflict = IGNORE)
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("delete from user where name like :badName")
    int deleteUsersByName(String badName);

    @Insert(onConflict = IGNORE)
    void insertOrReplaceUsers(User... users);

    @Delete
    void deleteUsers(User user1, User user2);

    @Query("DELETE FROM User")
    void deleteAll();
}
