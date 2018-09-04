package com.heiko.mysamplepaging.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author EthanCo
 * @since 2018/9/4
 */
@Entity
data class Student(@PrimaryKey(autoGenerate = true) val id:Int,val name:String)