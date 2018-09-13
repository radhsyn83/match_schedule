package com.fathurradhy.matchschedule.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.adapter.MatchSearchAdapter
import com.fathurradhy.matchschedule.mvp.model.MatchItem
import com.fathurradhy.matchschedule.mvp.model.SearchResponse
import com.fathurradhy.matchschedule.mvp.presenter.SearchPresenter
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import com.fathurradhy.matchschedule.test.repository.SearchView
import com.fathurradhy.matchschedule.utils.CustomProgressDialog
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast

class SearchActivity : AppCompatActivity(), SearchView, MatchSearchAdapter.Listener {

    lateinit var searchPresenter: SearchPresenter
    private lateinit var query: String

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        CustomProgressDialog.showDialog(this)

        query = intent.extras!!.getString("query")

        searchPresenter = SearchPresenter(this , RetrofitRepository())
        searchPresenter.getSearchTeam(query)

        swipeRefreshLayout.onRefresh {
            CustomProgressDialog.showDialog(this)
            searchPresenter.getSearchTeam(query)
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onDataLoaded(data: SearchResponse?) {
        CustomProgressDialog.stopDialog()

        Log.d("TEAM", data?.event?.size.toString())

        val team = arrayListOf<MatchItem>()

        for (i in 0 until data?.event?.size!!) {
            if (data?.event?.get(i)?.strSport == "Soccer") {
                team.add(data.event[i])
            }
        }

        rv_team.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        rv_team.adapter = MatchSearchAdapter(this, team, this)
    }

    override fun onDataError() {
        toast("No team found")
    }

    override fun onMatchClick(data: MatchItem) {
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
                "strAwayLineupSubstitutes" to data.strAwayLineupSubstitutes
        )
    }
}
