package com.fathurradhy.matchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.domain.model.MatchModelResult
import com.fathurradhy.matchschedule.domain.presenter.MatchImpls
import com.fathurradhy.matchschedule.domain.view.MatchView
import com.fathurradhy.matchschedule.utils.DateUtils
import kotlinx.android.synthetic.main.item_favorit.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import kotlin.collections.ArrayList

class FavoritAdapter(private val list: ArrayList<Any?>, val listener: Listener) : RecyclerView.Adapter<FavoritAdapter.ViewHolder>() {

    interface Listener {
        fun onMatchClick(data: MatchModelResult)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorit, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Any?) {

            MatchImpls(object : MatchView{
                override fun onSuccess(data: ArrayList<MatchModelResult>) {
                    if (data[0].intAwayScore == null && data[0].intHomeScore == null)
                        itemView.play.text = "COMING SOON"
                    else
                        itemView.play.text = "PAST MATCH"

                    itemView.date_match.text = DateUtils.dateFormat(data[0].dateEvent)
                    itemView.time_match.text = DateUtils.timeFormat(data[0].strTime)
                    itemView.home_team.text = data[0].strHomeTeam
                    itemView.away_team.text = data[0].strAwayTeam
                    itemView.home_score.text = data[0].intHomeScore
                    itemView.away_score.text = data[0].intAwayScore
                    itemView.container_success.visibility = View.VISIBLE
                    itemView.container_onload.visibility = View.GONE

                    itemView.cardview.onClick { listener.onMatchClick(data[0]) }

                }

                override fun onFailed(msg: String) {
                    Log.e("Favorite Adapter", msg)
                }
            }).loadMatchById(data.toString())

        }
    }

}
