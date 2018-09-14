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
import com.fathurradhy.matchschedule.activity.FavoritActivity
import com.fathurradhy.matchschedule.activity.TeamActivity
import com.fathurradhy.matchschedule.adapter.TeamAdapter
import com.fathurradhy.matchschedule.mvp.model.EventBusModel
import com.fathurradhy.matchschedule.mvp.model.LeaguesResponse
import com.fathurradhy.matchschedule.mvp.model.TeamResponse
import com.fathurradhy.matchschedule.mvp.model.TeamsItem
import com.fathurradhy.matchschedule.mvp.presenter.TeamPresenter
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import com.fathurradhy.matchschedule.test.repository.TeamView
import com.fathurradhy.matchschedule.utils.CustomProgressDialog
import kotlinx.android.synthetic.main.fragment_team.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.startActivity

class TeamFragment : Fragment(), TeamView, TeamAdapter.Listener, AdapterView.OnItemSelectedListener {

    lateinit var teamPresenter: TeamPresenter
    private val leaguesName = ArrayList<String>()
    private val leaguesId = ArrayList<String>()
    private var currentLeguesId = ""
    private var firtTimeLoad = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        leagues.setOnItemSelectedListener(this)

        teamPresenter = TeamPresenter(this, RetrofitRepository())

        KeyboardVisibilityEvent.setEventListener(activity!!) {
            if(!it)
                search_view.closeSearch()
        }

        favorit_btn.onClick { startActivity<FavoritActivity>() }
        search_btn.onClick { search_view.showSearch() }
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

    override fun onDataLoaded(data: TeamResponse?, side: String) {
        rv_team.layoutManager = GridLayoutManager(activity!!, 3, GridLayoutManager.VERTICAL, false)
        rv_team.adapter = data?.teams?.let { TeamAdapter(activity!!, it, this) }
        CustomProgressDialog.stopDialog()
    }

    override fun onDataError() {}

    override fun onMatchClick(data: TeamsItem) {
        startActivity<TeamActivity>(
                "idTeam" to data.idTeam,
                "strTeam" to data.strTeam,
                "strSport" to data.strSport,
                "idLeague" to data.idLeague,
                "strDescriptionEN" to data.strDescriptionEN,
                "strCountry" to data.strCountry,
                "strTeamBadge" to data.strTeamBadge,
                "strTeamJersey" to data.strTeamJersey,
                "strTeamLogo" to data.strTeamLogo,
                "strTeamFanart1" to data.strTeamFanart1,
                "strTeamFanart2" to data.strTeamFanart2,
                "strYoutube: " to data.strYoutube
        )
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (!firtTimeLoad) {
            CustomProgressDialog.showDialog(activity!!)
        }

        currentLeguesId = leaguesId[position]
        teamPresenter.getTeamByLeagues(currentLeguesId)

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
