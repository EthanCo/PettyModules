package per.lijuan.pagingdome

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import per.lijuan.pagingdome.paging.StudentRepository

/**
 * Created by juan on 2018/05/23.
 */
class StudentViewModel : AndroidViewModel {
    private lateinit var mPostRepository: StudentRepository

    constructor(application: Application, postRepository: StudentRepository):super(application){
        this.mPostRepository=postRepository
    }

    // region 基于Android官方Paging Library的分页加载框架
    private val data = MutableLiveData<String>()
    private val repoResult = Transformations.map(data, {
        mPostRepository.getStudentList(10)
    })

    val posts = Transformations.switchMap(repoResult, { it.pagedList })!!
    val networkState = Transformations.switchMap(repoResult, { it.networkState })!!
    val refreshState = Transformations.switchMap(repoResult, { it.refreshState })!!

    fun refresh() {
        repoResult.value?.refresh?.invoke()
    }

    fun showDatas(subreddit: String): Boolean {
        if (data.value == subreddit) {
            return false
        }
        data.value = subreddit
        return true
    }

    fun retry() {
        val listing = repoResult?.value
        listing?.retry?.invoke()
    }

    fun currentData(): String? = data.value

    // endregion
}