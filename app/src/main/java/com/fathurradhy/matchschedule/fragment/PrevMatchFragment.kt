package com.fathurradhy.matchschedule.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.activity.DetailMatchActivity
import com.fathurradhy.matchschedule.adapter.MatchAdapter
import com.fathurradhy.matchschedule.entity.EventsItem
import com.fathurradhy.matchschedule.entity.MatchResponse
import com.fathurradhy.matchschedule.match.MatchPresenter
import com.fathurradhy.matchschedule.match.MatchView
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import com.fathurradhy.matchschedule.utils.CustomProgressDialog
import kotlinx.android.synthetic.main.fragment_prev_match.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class PrevMatchFragment : Fragment(), MatchView, MatchAdapter.Listener {

    lateinit var matchPresenter: MatchPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_prev_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        matchPresenter = MatchPresenter(this , RetrofitRepository())

        matchPresenter.getPrevMatch("4328")

    }

    override fun onShowLoading() {
        CustomProgressDialog.showDialog(activity!!)
    }

    override fun onHideLoading() {
        CustomProgressDialog.stopDialog()
    }

    override fun onDataLoaded(data: MatchResponse?) {
        rv_team_prev.layoutManager = LinearLayoutManager(activity!!)
        rv_team_prev.adapter = data!!.events?.let { MatchAdapter(it, this)}
    }

    override fun onDataError() { toast("Error") }

    override fun onMatchClick(data: EventsItem, home_name: TextView, away_name: TextView) {
        startActivity<DetailMatchActivity>(
                "idHomeTeam" to data.idHomeTeam,
                "idAwayTeam" to data.idAwayTeam,
                "idEvent" to data.idEvent,
                "strHomeTeam" to data.strHomeTeam,
                "strAwayTeam" to data.strAwayTeam,
                "intHomeScore" to data.intHomeScore,
                "intAwayScore" to data.intAwayScore,
                "strDate" to data.strDate,
                "dateEvent" to data.dateEvent,
                "intHomeShots" to data.intHomeShots,
                "intAwayShots" to data.intAwayShots,
                "strTime" to data.strTime,
                "strHomeYellowCards" to data.strHomeYellowCards,
                "strAwayYellowCards" to data.strAwayYellowCards,
                "strHomeRedCards" to data.strHomeRedCards,
                "strAwayRedCards" to data.strAwayRedCards,
                "strHomeGoalDetails" to data.strHomeGoalDetails,
                "strAwayGoalDetails" to data.strAwayGoalDetails,
                "strHomeLineupGoalkeeper" to data.strHomeLineupGoalkeeper,
                "strHomeLineupDefense" to data.strHomeLineupDefense,
                "strHomeLineupMidfield" to data.strHomeLineupMidfield,
                "strHomeLineupForward" to data.strHomeLineupForward,
                "strHomeLineupSubstitutes" to data.strHomeLineupSubstitutes,
                "strAwayLineupGoalkeeper" to data.strAwayLineupGoalkeeper,
                "strAwayLineupDefense" to data.strAwayLineupDefense,
                "strAwayLineupMidfield" to data.strAwayLineupMidfield,
                "strAwayLineupForward" to data.strAwayLineupForward,
                "strAwayLineupSubstitutes" to data.strAwayLineupSubstitutes,
                "HOME_NAME" to ViewCompat.getTransitionName(home_name),
                "AWAY_NAME" to ViewCompat.getTransitionName(away_name)
        )
    }
}
