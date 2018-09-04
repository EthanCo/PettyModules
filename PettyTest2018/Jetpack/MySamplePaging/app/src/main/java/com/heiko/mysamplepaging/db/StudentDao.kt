package com.heiko.mysamplepaging.db

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * @author EthanCo
 * @since 2018/9/4
 */
@Dao
interface StudentDao {

    @Query("SELECT * FROM Student ORDER BY name COLLATE NOCASE ASC") //NOCASE
    fun getAllStudent(): DataSource.Factory<Int, Student>

    @Insert
    fun insert(students: List<Student>)

    @Insert
    fun insert(student: Student)
}