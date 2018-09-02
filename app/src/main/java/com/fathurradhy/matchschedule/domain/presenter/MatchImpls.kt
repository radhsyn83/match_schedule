package com.fathurradhy.matchschedule.domain.presenter

import android.util.Log
import com.fathurradhy.matchschedule.domain.model.MatchModel
import com.fathurradhy.matchschedule.domain.view.MatchView
import com.fathurradhy.matchschedule.net.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchImpls(val matchView: MatchView) : MatchPresenter {

    override fun loadPrevMatch() {
        val call = RetrofitConfig().getApi().getPrevMatch()
        call.enqueue(object : Callback<MatchModel> {
            override fun onResponse(call: Call<MatchModel>, response: Response<MatchModel>) {
                if (response.isSuccessful()) {
                    Log.d("TESTMATCH",""+response.body()?.events?.size)
                    response.body()?.events?.let { matchView.onSuccess(it) }
                } else {
                    matchView.onFailed("Gagal")
                }
            }

            override fun onFailure(call: Call<MatchModel>, t: Throwable) {
                matchView.onFailed(t.message.toString())
                Log.e("PrevMatchFragment", t.toString())
            }
        })
    }

    override fun loadNextMatch() {
        val call = RetrofitConfig().getApi().getNextMatch()
        call.enqueue(object : Callback<MatchModel> {
            override fun onResponse(call: Call<MatchModel>, response: Response<MatchModel>) {
                if (response.isSuccessful()) {
                    response.body()?.events?.let { matchView.onSuccess(it) }
                } else {
                    matchView.onFailed("Gagal")
                }
            }

            override fun onFailure(call: Call<MatchModel>, t: Throwable) {
                matchView.onFailed(t.message.toString())
                Log.e("PrevMatchFragment", t.toString())
            }
        })
    }

    override fun loadMatchById(id: String) {
        val call = RetrofitConfig().getApi().getMatchById(id)
        call.enqueue(object : Callback<MatchModel> {
            override fun onResponse(call: Call<MatchModel>, response: Response<MatchModel>) {
                if (response.isSuccessful()) {
                    response.body()?.events?.let { matchView.onSuccess(it) }
                } else {
                    matchView.onFailed("Gagal")
                }
            }

            override fun onFailure(call: Call<MatchModel>, t: Throwable) {
                matchView.onFailed(t.message.toString())
                Log.e("PrevMatchFragment", t.toString())
            }
        })
    }
}