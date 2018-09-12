package com.fathurradhy.matchschedule.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.adapter.FavoritAdapter
import com.fathurradhy.matchschedule.mvp.model.MatchItem
import com.fathurradhy.matchschedule.utils.Favorite
import com.fathurradhy.matchschedule.utils.database
import kotlinx.android.synthetic.main.activity_favorit.*
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SqlOrderDirection
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.startActivity

class FavoritActivity : AppCompatActivity() {

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorit)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true)

        loadFavorit()

        swipeRefreshLayout.setOnRefreshListener {
            loadFavorit()
        }
    }

    private fun loadFavorit(){

        try {
            database.use {
                val idEvent = ArrayList<Any?>()

                select(Favorite.TABLE_FAVORITE, Favorite.MATCH_ID)
                        .orderBy(Favorite.MATCH_ID, SqlOrderDirection.DESC)
                        .exec {
                            parseList(object: MapRowParser<ArrayList<Any?>> {
                                override fun parseRow(columns: Map<String, Any?>): ArrayList<Any?> {
                                    if (columnCount > 0){
                                        idEvent.add(columns.getValue(Favorite.MATCH_ID))
                                    }

                                    return idEvent
                                }
                            })
                        }

                val adapter = FavoritAdapter(idEvent, object : FavoritAdapter.Listener{
                    override fun onMatchClick(data: MatchItem) {
                        matchClick(data)
                    }
                })
                rv_team.layoutManager = LinearLayoutManager(this@FavoritActivity)
                rv_team.adapter = adapter

                swipeRefreshLayout.isRefreshing = false
            }
        } catch (e: SQLiteConstraintException){
            snackbar(swipeRefreshLayout, e.localizedMessage).show()
        }
    }

    private fun matchClick(data: MatchItem) {
        startActivity<DetailMatchActivity>(
                "idHomeTeam" to data.idHomeTeam,
                "idAwayTeam" to data.idAwayTeam,
                "idEvent" to data.idEvent,
                "strHomeTeam" to data.strHomeTeam,
                "strAwayTeam" to data.strAwayTeam,
                "intHomeScore" to data.intHomeScore,
                "intAwayScore" to data.intAwayScore,
                "strDate" to data.strDate,
                "dateEvent" to data.dateEvent,
                "intHomeShots" to data.intHomeShots,
                "intAwayShots" to data.intAwayShots,
                "strHomeYellowCards" to data.strHomeYellowCards,
                "strAwayYellowCards" to data.strAwayYellowCards,
                "strHomeRedCards" to data.strHomeRedCards,
                "strAwayRedCards" to data.strAwayRedCards,
                "strHomeGoalDetails" to data.strHomeGoalDetails,
                "strAwayGoalDetails" to data.strAwayGoalDetails,
                "strHomeLineupGoalkeeper" to data.strHomeLineupGoalkeeper,
                "strHomeLineupDefense" to data.strHomeLineupDefense,
                "strHomeLineupMidfield" to data.strHomeLineupMidfield,
                "strHomeLineupForward" to data.strHomeLineupForward,
                "strHomeLineupSubstitutes" to data.strHomeLineupSubstitutes,
                "strAwayLineupGoalkeeper" to data.strAwayLineupGoalkeeper,
                "strAwayLineupDefense" to data.strAwayLineupDefense,
                "strAwayLineupMidfield" to data.strAwayLineupMidfield,
                "strAwayLineupForward" to data.strAwayLineupForward,
                "strAwayLineupSubstitutes" to data.strAwayLineupSubstitutes,
                "strTime" to data.strTime
        )
    }
}
