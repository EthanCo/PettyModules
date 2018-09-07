package com.heiko.mypadingsample.view.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.heiko.mypadingsample.R
import com.heiko.mypadingsample.bean.Gateway

/**
 * @author EthanCo
 * @since 2018/9/4
 */
class GatewayAdapter : PagedListAdapter<Gateway, GatewayAdapter.GatewayViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GatewayViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_gateway, parent, false)
        return GatewayViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GatewayViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Gateway>() {
            override fun areItemsTheSame(oldItem: Gateway, newItem: Gateway): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Gateway, newItem: Gateway): Boolean {
                return oldItem == newItem
            }
        }
    }

    class GatewayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.tv_gateway_name)
        var gateway: Gateway? = null

        fun bind(gateway: Gateway?) {
            this.gateway = gateway
            tvName.text = gateway?.name
        }
    }
}
