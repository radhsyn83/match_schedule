package com.fathurradhy.matchschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.entity.EventsItem
import com.fathurradhy.matchschedule.entity.TeamResponse
import com.fathurradhy.matchschedule.match.TeamPresenter
import com.fathurradhy.matchschedule.match.TeamView
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import com.fathurradhy.matchschedule.utils.DateUtils
import com.fathurradhy.matchschedule.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.item_match.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*

class Match2Adapter(private val mContext: Context, private val list: List<EventsItem>, val listener: Listener) : RecyclerView.Adapter<Match2Adapter.ViewHolder>() {

    lateinit var teamPresenter: TeamPresenter

    interface Listener {
        fun onMatchClick(data: EventsItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view), TeamView {
        private var isAway = false
        private var isHome = false

        fun bind(event: EventsItem) {
//            itemView.date_match.text = data.dateEvent?.let { DateUtils.dateFormat(it) }
//            itemView.time_match.text = data.strTime?.let { DateUtils.timeFormat(it) }
//            itemView.home_team.text = data.strHomeTeam
//            itemView.away_team.text = data.strAwayTeam
//            itemView.home_score.text = data.intHomeScore
//            itemView.away_score.text = data.intAwayScore
//
//            itemView.cardview.onClick { listener.onMatchClick(data) }

            itemView.date_match.text = event.dateEvent?.let { DateUtils.dateFormat(it) }
            itemView.time_match.text = event.strTime?.let { DateUtils.timeFormat(it) }
            itemView.home_score.text = event.intHomeScore
            itemView.away_score.text = event.intAwayScore

            teamPresenter = TeamPresenter(this, RetrofitRepository())

        }

        private fun getHomeLogo(id: String) {
            teamPresenter.getEmblemHome(id)
        }

        private fun getAwayLogo(id: String) {
            teamPresenter.getEmblemAway(id)
        }

        override fun onShowLoading() {}

        override fun onHideLoading() {}

        override fun onDataLoaded(data: TeamResponse?, side: String) {
            if (side.equals("home")) {
                Glide.with(mContext)
                        .load(data?.teams?.get(0)?.strTeamBadge)
                        .apply(RequestOptions()
                                .placeholder(R.drawable.placeholder))
                        .into(itemView.home_logo)
                isHome = true
            } else {
                Glide.with(mContext)
                        .load(data?.teams?.get(0)?.strTeamBadge)
                        .apply(RequestOptions()
                                .placeholder(R.drawable.placeholder))
                        .into(itemView.away_logo)
                isAway = true
            }

            if (isAway && isHome) {
                EspressoIdlingResource.decrement()
            }
        }

        override fun onDataError() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}
