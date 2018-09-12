package com.fathurradhy.matchschedule.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.mvp.model.MatchItem
import com.fathurradhy.matchschedule.mvp.model.TeamResponse
import com.fathurradhy.matchschedule.mvp.presenter.TeamPresenter
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import com.fathurradhy.matchschedule.test.repository.TeamView
import com.fathurradhy.matchschedule.utils.DateUtils
import kotlinx.android.synthetic.main.item_match.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*


class MatchSearchAdapter(private val mContext: Context, private val list: List<MatchItem>, val listener: Listener) : RecyclerView.Adapter<MatchSearchAdapter.ViewHolder>() {

    lateinit var teamPresenter: TeamPresenter

    interface Listener {
        fun onMatchClick(data: MatchItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view), TeamView<TeamResponse?> {
        private var isAway = false
        private var isHome = false
        private var repeat = 0
        private var event: MatchItem? = null

        fun bind(event: MatchItem) {
            if (event.strSport.equals("Soccer")) {
                //SET ONLOAD LAYOUT
                itemView.success_layout.visibility = View.GONE
                itemView.load_layout.visibility = View.VISIBLE

                this.event = event
                teamPresenter = TeamPresenter(this, RetrofitRepository())

                //SET ITEM CLICK
                itemView.cardview.onClick {listener.onMatchClick(event)}
                itemView.reminder.onClick {onAddEventClicked(event)}

                //SET SCORE VIEW
                val score_home = event.intHomeScore ?: ""
                val score_away = event.intAwayScore ?: ""

                if ( score_home == "" && score_away == ""){
                    itemView.home_score.visibility = View.GONE
                    itemView.away_score.visibility = View.GONE
                    itemView.home_score2.visibility = View.GONE
                    itemView.away_score2.visibility = View.GONE
                    itemView.reminder.visibility = View.VISIBLE

                } else {
                    itemView.home_score.visibility = View.VISIBLE
                    itemView.away_score.visibility = View.VISIBLE
                    itemView.home_score2.visibility = View.VISIBLE
                    itemView.away_score2.visibility = View.VISIBLE
                    itemView.reminder.visibility = View.GONE
                    itemView.home_score.text = event.intHomeScore
                    itemView.away_score.text = event.intAwayScore

                    if (score_home > score_away)
                        itemView.home_score.setTypeface(itemView.home_score.getTypeface(), Typeface.BOLD)
                    else if (score_home < score_away)
                        itemView.away_score.setTypeface(itemView.away_score.getTypeface(), Typeface.BOLD)
                }

                //SET TIME AND DATE
                itemView.date_match.text = event.dateEvent?.let { DateUtils.dateFormat(it) }
                itemView.time_match.text = event.strTime?.let { DateUtils.timeFormat2(it) }

                //SET EMBLEM
                event.idHomeTeam?.let { teamPresenter.getEmblemHome(it) }
                event.idAwayTeam?.let { teamPresenter.getEmblemAway(it) }
            }
        }

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
                itemView.success_layout.visibility = View.VISIBLE
                itemView.load_layout.visibility = View.GONE
            }
        }

        override fun onDataError() {
            if (repeat <= 3) {
                //Reload Image
                event?.idHomeTeam?.let { teamPresenter.getEmblemHome(it) }
                event?.idAwayTeam?.let { teamPresenter.getEmblemAway(it) }
                repeat++
            } else {
                mContext.toast("Gagal meload jadwal")
            }
        }
    }

    fun onAddEventClicked(event: MatchItem) {
        val intent = Intent(Intent.ACTION_INSERT)
        intent.type = "vnd.android.cursor.item/event"

        val dateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ssXX").parse(event.dateEvent+" "+event.strTime)
        val calendar = Calendar.getInstance()
        calendar.time = dateTime

        val startTime = calendar.getTimeInMillis()
        val endTime = calendar.getTimeInMillis() + 60 * 60 * 1000

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime)
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)

        intent.putExtra(Events.TITLE, event.strHomeTeam+" vs "+event.strAwayTeam)

        mContext.startActivity(intent)
    }
}
