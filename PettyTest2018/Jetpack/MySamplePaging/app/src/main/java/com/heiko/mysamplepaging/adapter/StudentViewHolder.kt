package com.heiko.mysamplepaging.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.heiko.mysamplepaging.R
import com.heiko.mysamplepaging.db.Student

/**
 * @author EthanCo
 * @since 2018/9/4
 */
class StudentViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)) {
    private val nameView = itemView.findViewById<TextView>(R.id.name)
    var student: Student? = null

    fun bindTo(student: Student?) {
        this.student = student
        nameView.text = student?.name
    }
}