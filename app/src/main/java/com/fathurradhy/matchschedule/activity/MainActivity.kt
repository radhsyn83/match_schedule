package com.fathurradhy.matchschedule.activity

import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.adapter.FragmentViewPagerAdapter
import com.fathurradhy.matchschedule.adapter.MainPagerAdapter
import com.fathurradhy.matchschedule.fragment.MatchFragment
import com.fathurradhy.matchschedule.fragment.NextMatchFragment
import com.fathurradhy.matchschedule.fragment.PrevMatchFragment
import com.fathurradhy.matchschedule.fragment.TeamFragment
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    private var mFragmentViewPagerAdapter: FragmentViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity<SpalshActivity>()

        bottomBar.setOnTabSelectListener { tabId ->
            when (tabId) {
                R.id.tab_match -> {
                    viewpager.currentItem = 0
                    if (Build.VERSION.SDK_INT >= 21) {
                        val window = window
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                        window.statusBarColor = resources.getColor(R.color.colorPrimary)
                    }

                }
                R.id.tab_team -> {
                    viewpager.currentItem = 1
                    if (Build.VERSION.SDK_INT >= 21) {
                        val window = window
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                        window.statusBarColor = resources.getColor(R.color.colorTeamDark)
                    }
                }
            }
        }

        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                val fragment = (viewpager.adapter as FragmentViewPagerAdapter).getFragment(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        setupViewPager(viewpager)

        val limit = if (mFragmentViewPagerAdapter!!.count > 1) mFragmentViewPagerAdapter!!.count - 1 else 1
        viewpager.offscreenPageLimit = limit
    }

    private fun setupViewPager(viewPager: ViewPager) {
        mFragmentViewPagerAdapter = FragmentViewPagerAdapter(supportFragmentManager)
        val limit = if (mFragmentViewPagerAdapter!!.count > 1) mFragmentViewPagerAdapter!!.count - 1 else 1
        mFragmentViewPagerAdapter!!.addFragment(MatchFragment())
        mFragmentViewPagerAdapter!!.addFragment(TeamFragment())
        viewPager.adapter = mFragmentViewPagerAdapter
        viewPager.offscreenPageLimit = limit
    }
}
