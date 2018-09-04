package per.lijuan.pagingdome.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import per.lijuan.pagingdome.R
import per.lijuan.pagingdome.paging.StudentBean

/**
 * Created by juan on 2018/05/23.
 */
class StudentViewHolder(parent: ViewGroup):RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_weight,parent,false)) {
    private val mTvName = itemView.findViewById<TextView>(R.id.tv_name)
    var studentBean : StudentBean? = null

    fun bindTo(bean : StudentBean?, mContext: Context) {
        this.studentBean = bean
        mTvName.text = studentBean!!.name
    }
}