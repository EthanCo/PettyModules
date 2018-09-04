package per.lijuan.pagingdome.paging

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList

/**
 * UI显示列表和系统其余部分进行交互所必需的数据类
 * Created by juan on 2018/05/23.
 */
data class Listing<T> (
        val pagedList: LiveData<PagedList<T>>,
        val networkState: LiveData<Resource<String>>,
        val refreshState: LiveData<Resource<String>>,
        val refresh: () -> Unit,
        val retry: () -> Unit)