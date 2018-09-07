package com.heiko.mypadingsample.bean

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author EthanCo
 * @since 2018/9/4
 */
//TODdata
@Entity
class Gateway(@PrimaryKey(autoGenerate = true) val id : Int, val name : String) {
}