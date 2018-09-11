package com.fathurradhy.matchschedule.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.fathurradhy.matchschedule.R
import org.jetbrains.anko.startActivity

class SpalshActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)

        Handler().postDelayed({
                startActivity<MainActivity>()
        }, 2000)
    }
}
