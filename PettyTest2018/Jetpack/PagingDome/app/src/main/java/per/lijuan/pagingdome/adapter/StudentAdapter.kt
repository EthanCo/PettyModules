package per.lijuan.pagingdome.adapter

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import per.lijuan.pagingdome.paging.StudentBean

/**
 * Created by juan on 2018/05/23.
 */
class StudentAdapter(private val mContext: Context):PagedListAdapter<StudentBean, StudentViewHolder>(diffCallback){

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bindTo(getItem(position),mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder = StudentViewHolder(parent)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<StudentBean>() {
            override fun areItemsTheSame(oldItem: StudentBean, newItem: StudentBean): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: StudentBean, newItem: StudentBean): Boolean =
                    oldItem == newItem
        }
    }
}