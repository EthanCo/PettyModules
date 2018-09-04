package per.lijuan.pagingdome.paging


/**
 * 给不同的Repository实现共享的通用接口
 * Created by juan on 2018/05/23.
 */
interface StudentRepository {
    fun getStudentList(pageSize: Int): Listing<StudentBean?>
}