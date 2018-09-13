package com.fathurradhy.matchschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.mvp.model.PlayerItem
import kotlinx.android.synthetic.main.item_player.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class PlayerAdapter(private val mContext: Context, private val list: List<PlayerItem>, val listener: Listener) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    interface Listener {
        fun onMatchClick(data: PlayerItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(data: PlayerItem) {
            Glide.with(mContext)
                    .load(data.strCutout)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.placeholder_user))
                    .into(itemView.profile_image)

            itemView.player_name.text = data.strPlayer
            itemView.player_role.text = data.strPosition

            itemView.cardview.onClick {
                listener.onMatchClick(data)
            }
        }
    }
}
