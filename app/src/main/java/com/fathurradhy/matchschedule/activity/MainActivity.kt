package com.fathurradhy.matchschedule.activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.adapter.MainPagerAdapter
import com.fathurradhy.matchschedule.fragment.NextMatchFragment
import com.fathurradhy.matchschedule.fragment.PrevMatchFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {

    private var menuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initTabs()
    }

    private fun initTabs() {
        setupViewPager(viewpager)
        tabs_layout.setupWithViewPager(viewpager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = MainPagerAdapter(supportFragmentManager)
        adapter.add(PrevMatchFragment(), "Prev Match")
        adapter.add(NextMatchFragment(), "Next Match")
        viewPager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorit_menu, menu)
        menuItem = menu?.findItem(R.id.add_to_favorite)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_to_favorite -> {
                startActivity<FavoritActivity>()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
