package com.heiko.mysamplepaging

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.heiko.mysamplepaging.db.Student
import com.heiko.mysamplepaging.db.StudentDb

/**
 * @author EthanCo
 * @since 2018/9/4
 */
class MainViewModel(app: Application) : AndroidViewModel(app) {

    val dao = StudentDb.get(app).studentDao()

    val allStudents = LivePagedListBuilder(dao.getAllStudent(), PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE) //配置分页加载的数量
            .setEnablePlaceholders(ENABLE_PLACEHOLDERS) //配置是否启动PlaceHolders
            .setInitialLoadSizeHint(PAGE_SIZE)  //初始化加载的数量
            .build()).build()

    fun insertStudent(name: String) {
        dao.insert(Student(id = 0, name = name))
    }

    companion object {
        private const val PAGE_SIZE = 15
        private const val ENABLE_PLACEHOLDERS = false
    }
}
