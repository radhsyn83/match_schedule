package com.fathurradhy.matchschedule.test.repository

import com.fathurradhy.matchschedule.entity.MatchResponse
import com.fathurradhy.matchschedule.entity.TeamResponse
import com.fathurradhy.matchschedule.network.ApiRepository
import com.fathurradhy.matchschedule.network.MyRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitRepository {

    fun getPrevMatch(id: String, callback: MatchRepositoryCallback<MatchResponse?>) {
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

    fun getNextMatch(id: String, callback: MatchRepositoryCallback<MatchResponse?>) {
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

    fun getDetailMatch(id: String, callback: MatchRepositoryCallback<MatchResponse?>) {
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

    fun getEmblemHome(id: String, callback: TeamRepositoryCallback<TeamResponse?>) {
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

    fun getEmblemAway(id: String, callback: TeamRepositoryCallback<TeamResponse?>) {
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
}