package com.fathurradhy.matchschedule.domain.presenter

import android.util.Log
import com.fathurradhy.matchschedule.domain.model.MatchModel
import com.fathurradhy.matchschedule.domain.model.TeamModel
import com.fathurradhy.matchschedule.domain.view.MatchView
import com.fathurradhy.matchschedule.domain.view.TeamView
import com.fathurradhy.matchschedule.net.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamImpls(val teamView: TeamView) : TeamPresenter {

    override fun loadTeamDetail(team_id: String) {
        val call = RetrofitConfig().getApi().getTeam(team_id)
        call.enqueue(object : Callback<TeamModel> {
            override fun onResponse(call: Call<TeamModel>, response: Response<TeamModel>) {
                if (response.isSuccessful()) {
                    val res = response.body()
                    res?.let { teamView.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<TeamModel>, t: Throwable) {
                Log.e("PrevMatchFragment", t.toString())
            }
        })
    }
}