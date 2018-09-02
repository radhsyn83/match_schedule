package com.fathurradhy.matchschedule.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.activity.DetailMatchActivity
import com.fathurradhy.matchschedule.adapter.MatchAdapter
import com.fathurradhy.matchschedule.domain.presenter.MatchImpls
import com.fathurradhy.matchschedule.domain.view.MatchView
import com.fathurradhy.matchschedule.domain.model.MatchModelResult
import com.fathurradhy.matchschedule.utils.CustomProgressDialog
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class NextMatchFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    var root: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_next_match, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = root?.findViewById(R.id.rv_team)!!

        loadData()

        swipeRefreshLayout.onRefresh { loadData() }
    }

    private fun loadData() {
        swipeRefreshLayout.isRefreshing = false

        MatchImpls(object : MatchView {
            override fun onSuccess(matchModelResult: ArrayList<MatchModelResult>) {
                CustomProgressDialog.stopDialog()
                val adapter = MatchAdapter(matchModelResult, object : MatchAdapter.Listener{
                    override fun onMatchClick(data: MatchModelResult) {
                        matchClick(data)
                    }
                })
                rv_team.adapter = adapter
            }

            override fun onFailed(msg: String) {
                toast(msg)
            }
        }).loadNextMatch()

    }

    private fun matchClick(data: MatchModelResult) {
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
                "strTime" to data.strTime

        )
    }

}