package com.heiko.architercturelogintest.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.heiko.architercturelogintest.bean.LoginConfig;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * 登录 
 *
 * @author EthanCo
 * @since 2018/2/24
 */
@Dao
public interface LoginDao {
    @Query("select * from LoginConfig")
    List<LoginConfig> loadAllLoginConfigs();

    /*@Query("select * from LoginConfig where id = :id")
    LoginConfig loadLoginConfigById(int id);*/

//    @Query("SELECT MAX(id) FROM LoginConfig")
//    int findMaxId();

    @Query("SELECT * FROM LoginConfig WHERE LastModified=(SELECT MAX(LastModified) FROM LoginConfig) LIMIT 1")
    LoginConfig findLastEditConfig();

    @Query("select * from LoginConfig where userName = :userName ")
    List<LoginConfig> findLoginConfigByName(String userName);

    @Insert(onConflict = IGNORE)
    void insertLoginConfig(LoginConfig loginConfig);

    @Delete
    void deleteLoginConfig(LoginConfig loginConfig);

//    @Query("delete from LoginConfig where name like :badName")
//    int deleteLoginConfigsByName(String badName);

    @Insert(onConflict = IGNORE)
    void insertOrReplaceLoginConfigs(LoginConfig... loginConfigs);

    @Delete
    void deleteLoginConfigs(LoginConfig loginConfig1, LoginConfig loginConfig2);

    @Query("DELETE FROM LoginConfig")
    void deleteAll();

    @Update
    void updateLoginConfigs(LoginConfig... loginConfigs);

    @Update
    void updateLoginConfigs(List<LoginConfig> loginConfigs);
}
