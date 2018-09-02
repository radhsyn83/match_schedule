package com.fathurradhy.matchschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.ArrayList

class MainPagerAdapter(mFragmentManager: FragmentManager) : FragmentPagerAdapter(mFragmentManager) {
    private val tabNames: ArrayList<String>
    private val fragments: ArrayList<Fragment>

    init {
        tabNames = ArrayList()
        fragments = ArrayList()
    }

    fun add(fragment: Fragment, title: String) {
        tabNames.add(title)
        fragments.add(fragment)
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabNames[position]
    }
}