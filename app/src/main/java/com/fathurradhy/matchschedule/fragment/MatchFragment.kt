package com.fathurradhy.matchschedule.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.activity.FavoritActivity
import com.fathurradhy.matchschedule.activity.SearchActivity
import com.fathurradhy.matchschedule.adapter.MainPagerAdapter
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.fragment_match.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity


class MatchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTabs()

        search_view.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                activity?.startActivity<SearchActivity>("query" to query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        favorit_btn.onClick { startActivity<FavoritActivity>() }
        search_btn.onClick { search_view.showSearch() }

        KeyboardVisibilityEvent.setEventListener(activity!!) {
           if(!it)
               search_view.closeSearch()
        }
    }

    private fun initTabs() {
        setupViewPager(viewpager)
        tabs_layout.setupWithViewPager(viewpager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = MainPagerAdapter(childFragmentManager)

        adapter?.add(NextMatchFragment(), "Next Match")
        adapter?.add(PrevMatchFragment(), "Prev Match")
        viewPager.adapter = adapter
    }
}
