package com.heiko.roomtest;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;
import io.reactivex.Flowable;

import java.util.List;

/**
 * UserDao
 *
 * @author Heiko
 * @date 2019/5/29
 */
@Dao
public interface UserDao {  // 定义成接口
    // Query注解定义查询, 参数是sql语句
    @Query("SELECT * FROM user")
    List<User> getAll();

    // 根据id查询user, :id这里意思是引用findById方法里参数id。是room定义固定写法：冒号+参数名称
    @Query("SELECT * FROM user WHERE id = :id")
    User findById(String id);
    //Update注解定义更新User
    @Update
    void udapte(User user);
    //Insert注解定义插入User
    @Insert
    void insert(User user);
    //Delete注解定义删除User
    @Delete
    void delete(User user);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getUsersLiveData();

    @Query("SELECT * FROM user")
    Flowable<List<User>> getUsersFlowable();
}
