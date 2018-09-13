package com.fathurradhy.matchschedule.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ArrayAdapter
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.mvp.model.EventBusModel
import com.fathurradhy.matchschedule.mvp.model.LeaguesResponse
import com.fathurradhy.matchschedule.mvp.presenter.LeaguesPresenter
import com.fathurradhy.matchschedule.test.repository.LeaguesView
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.act
import org.jetbrains.anko.startActivity

class SpalshActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)

        LeaguesPresenter(object : LeaguesView {
            override fun onDataLoaded(data: LeaguesResponse?) {

                EventBus.getDefault().post(EventBusModel<LeaguesResponse?>(resources.getString(R.string.leagues_bus), data))

                Handler().postDelayed({
                    finish()
                }, 2000)
            }

            override fun onDataError() {

            }
        }, RetrofitRepository()).getLeagues()
    }
}
