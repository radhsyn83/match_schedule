package com.fathurradhy.matchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.entity.EventsItem
import com.fathurradhy.matchschedule.utils.DateUtils
import kotlinx.android.synthetic.main.item_prev_match.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*

class MatchAdapter(private val list: List<EventsItem>, val listener: Listener) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    interface Listener {
        fun onMatchClick(data: EventsItem, home_name: TextView, away_name: TextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prev_match, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(data: EventsItem) {
            itemView.date_match.text = data.dateEvent?.let { DateUtils.dateFormat(it) }
            itemView.time_match.text = data.strTime?.let { DateUtils.timeFormat(it) }
            itemView.home_team.text = data.strHomeTeam
            itemView.away_team.text = data.strAwayTeam
            itemView.home_score.text = data.intHomeScore
            itemView.away_score.text = data.intAwayScore

            itemView.cardview.onClick { listener.onMatchClick(data, itemView.home_team, itemView.away_team) }
        }
    }

    private fun dateFormat(time: String) : String {
        val dateTime = SimpleDateFormat("yyyy-MM-dd").parse(time)
        val calendar = Calendar.getInstance()
        calendar.setTime(dateTime)

        val today = Calendar.getInstance()
        val yesterday = Calendar.getInstance()
        yesterday.add(Calendar.DATE, +1)

        val dateTime2 = SimpleDateFormat("yyyy-MM-dd").parse(time)
        val calendar2 = Calendar.getInstance()
        calendar2.setTime(dateTime2)

        if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
            return "Today"
        } else if (calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == yesterday.get(Calendar.DAY_OF_YEAR)) {
            return "Tomorrow"
        } else {
            return SimpleDateFormat("dd MMM yyyy").format(dateTime)
        }
    }
}
