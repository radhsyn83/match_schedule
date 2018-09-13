package com.fathurradhy.matchschedule.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.activity.DetailMatchActivity
import com.fathurradhy.matchschedule.adapter.MatchAdapter
import com.fathurradhy.matchschedule.mvp.model.EventBusModel
import com.fathurradhy.matchschedule.mvp.model.LeaguesResponse
import com.fathurradhy.matchschedule.mvp.model.MatchItem
import com.fathurradhy.matchschedule.mvp.model.MatchResponse
import com.fathurradhy.matchschedule.mvp.presenter.MatchPresenter
import com.fathurradhy.matchschedule.test.repository.MatchView
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import com.fathurradhy.matchschedule.utils.CustomProgressDialog
import com.fathurradhy.matchschedule.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class NextMatchFragment : Fragment(), MatchView, MatchAdapter.Listener, AdapterView.OnItemSelectedListener {

    lateinit var matchPresenter: MatchPresenter
    private val leaguesName = ArrayList<String>()
    private val leaguesId = ArrayList<String>()
    private var currentLeguesId = ""
    private var firtTimeLoad = true;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CustomProgressDialog.showDialog(activity!!)

//        EspressoIdlingResource.increment()
        matchPresenter = MatchPresenter(this , RetrofitRepository())

        swipeRefreshLayout.setOnRefreshListener {
            EspressoIdlingResource.increment()
            CustomProgressDialog.showDialog(activity!!)
            swipeRefreshLayout.isRefreshing = false
            matchPresenter.getNextMatch(currentLeguesId)
        }

        leagues.setOnItemSelectedListener(this)
    }

    private fun loadLeagues(data: LeaguesResponse) {
        val item = data?.leagues

        for (i in 0 until 9) {
            if (item?.get(i)?.strSport == "Soccer") {
                leaguesId.add(item[i].idLeague.toString())
                item[i].strLeague?.let { leaguesName.add(it) }
            }
        }

        val adapter = ArrayAdapter<String>(activity,
                R.layout.spinner_item, leaguesName)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        leagues.adapter = adapter
    }

    override fun onDataLoaded(data: MatchResponse?) {
        rv_team_next.layoutManager = GridLayoutManager(activity!!, 2, GridLayoutManager.VERTICAL, false)
        rv_team_next.adapter = data!!.events?.let { MatchAdapter(activity!!,it, this) }
        CustomProgressDialog.stopDialog()
//        EspressoIdlingResource.decrement()
    }

    override fun onDataError() { toast("Error") }

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

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (!firtTimeLoad) {
            EspressoIdlingResource.increment()
            CustomProgressDialog.showDialog(activity!!)
        }

        currentLeguesId = leaguesId[position]
        matchPresenter.getNextMatch(currentLeguesId)

        firtTimeLoad = false
    }

    @Subscribe
    fun onEvent(event: EventBusModel<LeaguesResponse>) {
        when (event.msg) {
            activity?.resources?.getString(R.string.leagues_bus) -> {
                event.list?.let { loadLeagues(it) }
            }
        }

    }

    override fun onDetach() {
        super.onDetach()
        EventBus.getDefault().unregister(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        EventBus.getDefault().register(this)
    }
}
