package com.fathurradhy.matchschedule.test.repository

import android.util.Log
import com.fathurradhy.matchschedule.mvp.model.LeaguesResponse
import com.fathurradhy.matchschedule.mvp.model.MatchResponse
import com.fathurradhy.matchschedule.mvp.model.SearchResponse
import com.fathurradhy.matchschedule.mvp.model.TeamResponse
import com.fathurradhy.matchschedule.net.ApiRepository
import com.fathurradhy.matchschedule.net.MyRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitRepository {

    fun getSearchTeam(e: String, callback: SearchView) {
        MyRetrofit
                .createService(ApiRepository::class.java)
                .getSearchTeam(e)
                .enqueue(object : Callback<SearchResponse?> {
                    override fun onFailure(call: Call<SearchResponse?>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<SearchResponse?>?, response: Response<SearchResponse?>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                Log.e("ERRORBOS", it.body().toString())
                                callback.onDataLoaded(it.body())
                            } else {
                                callback.onDataError()
                            }
                        }
                    }
                })
    }

    fun getPrevMatch(id: String, callback: MatchView) {
        MyRetrofit
                .createService(ApiRepository::class.java)
                .getPassMatch(id)
                .enqueue(object : Callback<MatchResponse?> {
                    override fun onFailure(call: Call<MatchResponse?>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<MatchResponse?>?, response: Response<MatchResponse?>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                callback.onDataLoaded(it.body())
                            } else {
                                callback.onDataError()
                            }
                        }
                    }
                })
    }

    fun getNextMatch(id: String, callback: MatchView) {
        MyRetrofit
                .createService(ApiRepository::class.java)
                .getNextMatch(id)
                .enqueue(object : Callback<MatchResponse?> {
                    override fun onFailure(call: Call<MatchResponse?>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<MatchResponse?>?, response: Response<MatchResponse?>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                callback.onDataLoaded(it.body())
                            } else {
                                callback.onDataError()
                            }
                        }
                    }
                })
    }

    fun getDetailMatch(id: String, callback: MatchView) {
        MyRetrofit
                .createService(ApiRepository::class.java)
                .getMatchById(id)
                .enqueue(object : Callback<MatchResponse?> {
                    override fun onFailure(call: Call<MatchResponse?>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<MatchResponse?>?, response: Response<MatchResponse?>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                callback.onDataLoaded(it.body())
                            } else {
                                callback.onDataError()
                            }
                        }
                    }
                })
    }

    fun getEmblemHome(id: String, callback: TeamView<TeamResponse?>) {
        MyRetrofit
                .createService(ApiRepository::class.java)
                .getTeam(id)
                .enqueue(object : Callback<TeamResponse?> {
                    override fun onFailure(call: Call<TeamResponse?>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<TeamResponse?>?, response: Response<TeamResponse?>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                callback.onDataLoaded(it.body(),"home")
                            } else {
                                callback.onDataError()
                            }
                        }
                    }
                })
    }

    fun getEmblemAway(id: String, callback: TeamView<TeamResponse?>) {
        MyRetrofit
                .createService(ApiRepository::class.java)
                .getTeam(id)
                .enqueue(object : Callback<TeamResponse?> {
                    override fun onFailure(call: Call<TeamResponse?>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<TeamResponse?>?, response: Response<TeamResponse?>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                callback.onDataLoaded(it.body(),"away")
                            } else {
                                callback.onDataError()
                            }
                        }
                    }
                })
    }

    fun getLeagues(callback: LeaguesView) {
        MyRetrofit
                .createService(ApiRepository::class.java)
                .getLeagues()
                .enqueue(object : Callback<LeaguesResponse?> {
                    override fun onFailure(call: Call<LeaguesResponse?>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<LeaguesResponse?>?, response: Response<LeaguesResponse?>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                callback.onDataLoaded(it.body())
                            } else {
                                callback.onDataError()
                            }
                        }
                    }
                })
    }
}