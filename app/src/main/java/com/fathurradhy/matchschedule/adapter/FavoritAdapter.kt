package com.fathurradhy.matchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.entity.EventsItem
import com.fathurradhy.matchschedule.entity.MatchResponse
import com.fathurradhy.matchschedule.match.MatchPresenter
import com.fathurradhy.matchschedule.match.MatchView
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import com.fathurradhy.matchschedule.utils.DateUtils
import kotlinx.android.synthetic.main.item_favorit.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class FavoritAdapter(private val list: ArrayList<Any?>, val listener: Listener) : RecyclerView.Adapter<FavoritAdapter.ViewHolder>() {

    lateinit var matchPresenter: MatchPresenter

    interface Listener {
        fun onMatchClick(data: EventsItem)
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

            matchPresenter = MatchPresenter(object : MatchView {
                override fun onShowLoading() {}

                override fun onHideLoading() {}

                override fun onDataLoaded(data: MatchResponse?) {
                    val match = data?.events?.get(0)

                    if (match?.intAwayScore == null && match?.intHomeScore == null)
                        itemView.play.text = "COMING SOON"
                    else
                        itemView.play.text = "PAST MATCH"

                    itemView.date_match.text = match?.dateEvent?.let { DateUtils.dateFormat(it) }
                    itemView.time_match.text = match?.strTime?.let { DateUtils.timeFormat(it) }
                    itemView.home_team.text = match?.strHomeTeam
                    itemView.away_team.text = match?.strAwayTeam
                    itemView.home_score.text = match?.intHomeScore
                    itemView.away_score.text = match?.intAwayScore
                    itemView.container_success.visibility = View.VISIBLE
                    itemView.container_onload.visibility = View.GONE

                    itemView.cardview.onClick { match?.let { it1 -> listener.onMatchClick(it1) } }
                }

                override fun onDataError() {}
            }, RetrofitRepository())

            matchPresenter.getDetailMatch(data.toString())

        }
    }
}
