package com.fathurradhy.matchschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.ViewGroup
import java.util.ArrayList
import java.util.HashMap

/**
 * Created by FathurRadhy on 3/27/2018.
 */
class FragmentViewPagerAdapter(private val mFragmentManager: FragmentManager) : FragmentPagerAdapter(mFragmentManager) {
    private var mFragmentTags: MutableMap<Int, String>? = null

    private var mFragmentList = ArrayList<Fragment>()

    init {
        this.mFragmentTags = HashMap<Int, String>()
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val obj = super.instantiateItem(container, position)
        if (obj is Fragment) {
            val f = obj
            val tag = f.tag
            mFragmentTags!!.put(position, tag!!)
        }
        return obj
    }

    fun getFragment(position: Int): Fragment? {
        val tag = mFragmentTags!![position] ?: return null
        return mFragmentManager.findFragmentByTag(tag)
    }
}