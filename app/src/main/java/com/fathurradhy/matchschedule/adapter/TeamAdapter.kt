package com.fathurradhy.matchschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.mvp.model.TeamsItem
import kotlinx.android.synthetic.main.item_team.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick


class TeamAdapter(private val mContext: Context, private val list: List<TeamsItem>, val listener: Listener) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    interface Listener {
        fun onMatchClick(data: TeamsItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(data: TeamsItem) {
            Glide.with(mContext)
                    .load(data.strTeamBadge)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.placeholder))
                    .into(itemView.logo)

            itemView.cardview.onClick {
                listener.onMatchClick(data)
            }
        }
    }
}
