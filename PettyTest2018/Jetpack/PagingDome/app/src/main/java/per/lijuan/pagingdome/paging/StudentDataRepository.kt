package per.lijuan.pagingdome.paging

import android.arch.lifecycle.Transformations.switchMap
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import java.util.concurrent.Executor

/**
 * Repository实现返回一个直接从网络加载数据的Listing，并使用该名称作为加载上一页/下一页数据的关键
 * Created by juan on 2018/05/23.
 */
class StudentDataRepository(private val executor: Executor): StudentRepository {
    override fun getStudentList(pageSize: Int): Listing<StudentBean?> {
        val sourceFactory= StudentDataSourceFactory(executor)
        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(pageSize*2)//定义第一页加载项目的数量
                .setPageSize(pageSize)//定义从DataSource中每一次加载的项目数量
                .build()
        val pagedList = LivePagedListBuilder(sourceFactory, pagedListConfig)
                .setFetchExecutor(executor)//设置Executor执行器用于从用于从DataSources中获取PagedLists数据，如果未设置，则默认为Arch组件I/O线程。
                .build()
        val refreshState = switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }
        return Listing<StudentBean?>(
                pagedList =pagedList,
                networkState = switchMap(sourceFactory.sourceLiveData, {
                    it.networkState
                }),
                retry = {
                    sourceFactory.sourceLiveData.value?.retryAllFailed()
                },
                refresh = {
                    sourceFactory.sourceLiveData.value?.invalidate()
                },
                refreshState = refreshState)
    }
}