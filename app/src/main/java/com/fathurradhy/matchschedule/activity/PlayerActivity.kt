package com.fathurradhy.matchschedule.activity

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.utils.DateUtils
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        setSupportActionBar(toolbar)
        title = "Profile"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        if (Build.VERSION.SDK_INT >= 21) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = resources.getColor(R.color.colorTeamDark)
        }

        Glide.with(this)
                .load(intent.getStringExtra("strCutout"))
                .apply(RequestOptions()
                        .placeholder(R.drawable.placeholder))
                .into(image)

        Glide.with(this)
                .load(intent.getStringExtra("strFanart1"))
                .apply(RequestOptions()
                        .placeholder(R.drawable.placeholder))
                .into(image_bg)

        val string = intent.getStringExtra("strDescriptionEN")
        val des = string.split(".")

        name.text = intent.getStringExtra("strPlayer")
        position.text = intent.getStringExtra("strPosition")
        team.text = intent.getStringExtra("strTeam")
        sport.text = intent.getStringExtra("strSport")
        born.text = DateUtils.dateFormat(intent.getStringExtra("dateBorn"))
        birth_location.text = intent.getStringExtra("strBirthLocation")
        height.text = intent.getStringExtra("strHeight")
        weight.text = intent.getStringExtra("strWeight")
        desc.text = des[0]
    }
}
