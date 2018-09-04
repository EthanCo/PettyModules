package per.lijuan.pagingdome.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.ItemKeyedDataSource
import android.util.Log
import java.util.concurrent.Executor

/**
 * DataSource负责加载第一页以及后面每一页数据
 * Created by juan on 2018/05/23.
 */
open class StudentDataSource(private val retryExecutor: Executor) : ItemKeyedDataSource<String, StudentBean>(){
    private var TAG: String="paging"
    private var retry:(()->Any)?=null
    private var startPosition:Int = 0

    fun retryAllFailed(){
        val prevRetry=retry
        retry=null
        prevRetry?.let {
            retryExecutor.execute { it.invoke() }
        }
    }

    val networkState by lazy{
        MutableLiveData<Resource<String>>()
    }
    val initialLoad by lazy{
        MutableLiveData<Resource<String>>()
    }

    /**
     * 接收初始加载的数据，在这里需要将获取到的数据通过LoadInitialCallback的onResult进行回调，用于出始化PagedList，并对加载的项目进行计数
     */
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<StudentBean>) {
        Log.d(TAG,"loadInitial->mSkip:"+startPosition+",count:"+params.requestedLoadSize)
        networkState.postValue(Resource.loading(null))
        initialLoad.postValue(Resource.loading(null))

        //模拟耗时操作
        val list=loadData(startPosition,params.requestedLoadSize)
        retry=null
        networkState.postValue(Resource.success(null))
        initialLoad.postValue(Resource.success(null))
        callback.onResult(list)
        startPosition+=list.size
    }

    /**
     * 接收加载的数据
     */
    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<StudentBean>) {
        Log.d(TAG,"loadAfter->mSkip:"+startPosition+",count:"+params.requestedLoadSize)
        networkState.postValue(Resource.loading(null))

        //模拟耗时操作
        val list=loadData(startPosition,params.requestedLoadSize)
        retry=null
        networkState.postValue(Resource.success(null))
        callback.onResult(list)
        startPosition+=list.size
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<StudentBean>) {
        Log.d(TAG,"loadBefore")
    }

    override fun getKey(item: StudentBean): String  = item.id!!

    /**
     * 模拟耗时操作，假设这里需要做一些后台线程的数据加载任务。
     */
    private fun loadData(startPosition: Int, limit: Int): List<StudentBean> {
        val list = ArrayList<StudentBean>()

        for (i in 0 until limit) {
            var position=startPosition + i
            val data = StudentBean(position.toString(), "学生@$position")
            list.add(data)
        }
        return list
    }
}