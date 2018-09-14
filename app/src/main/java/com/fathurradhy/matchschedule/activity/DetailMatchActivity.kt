package com.fathurradhy.matchschedule.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.mvp.model.TeamResponse
import com.fathurradhy.matchschedule.mvp.presenter.TeamPresenter
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import com.fathurradhy.matchschedule.test.repository.TeamView
import com.fathurradhy.matchschedule.utils.*
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.db.*
import org.jetbrains.anko.design.snackbar

class DetailMatchActivity : AppCompatActivity(), TeamView {
    override fun onDataLoaded(data: TeamResponse?, side: String) {
        if (side.equals("home")) {
            Glide.with(this@DetailMatchActivity)
                    .load(data?.teams?.get(0)?.strTeamBadge)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.placeholder))
                    .into(home_logo)
            home_logo.visibility = View.VISIBLE
            isHome = true
        } else {
            Glide.with(this@DetailMatchActivity)
                    .load(data?.teams?.get(0)?.strTeamBadge)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.placeholder))
                    .into(away_logo)
            away_logo.visibility = View.VISIBLE
            isAway = true
        }

        if (isAway && isHome) {
            EspressoIdlingResource.decrement()
        }

    }

    override fun onDataError() {

    }

    lateinit var teamPresenter: TeamPresenter

    private var isAway = false
    private var isHome = false
    private var menuItem: MenuItem? = null
    private var isFavorite: Boolean = false
    private lateinit var idEvent: String
    private lateinit var from: String

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        from = resources.getString(R.string.match)

        if (intent.extras!!.getString("EXTRA_ANIMAL_IMAGE_TRANSITION_NAME") != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                home_team.setTransitionName(intent.extras!!.getString("HOME_NAME"))
                away_team.setTransitionName(intent.extras!!.getString("AWAY_NAME"))
            }
        }

        EspressoIdlingResource.increment()

        teamPresenter = TeamPresenter(this, RetrofitRepository())

        idEvent = intent.getStringExtra("idEvent")

        getHomeLogo(intent.getStringExtra("idHomeTeam"))
        getAwayLogo(intent.getStringExtra("idAwayTeam"))

        date_match.text = DateUtils.dateFormat(intent.getStringExtra("dateEvent"))
        time_match.text = DateUtils.timeFormat(intent.getStringExtra("strTime"))

        home_team.text = intent.getStringExtra("strHomeTeam")
        home_score.text = intent.getStringExtra("intHomeScore")
        home_goals.text = intent.getStringExtra("strHomeGoalDetails")
        home_red_card.text = intent.getStringExtra("strHomeRedCards")
        home_yellow_card.text = intent.getStringExtra("strHomeYellowCards")
        home_goalkeeper.text = intent.getStringExtra("strHomeLineupGoalkeeper")
        home_deffense.text = intent.getStringExtra("strHomeLineupDefense")
        home_midfield.text = intent.getStringExtra("strHomeLineupMidfield")
        home_forwards.text = intent.getStringExtra("strHomeLineupForward")
        home_sub.text = intent.getStringExtra("strHomeLineupSubstitutes")

        away_team.text = intent.getStringExtra("strAwayTeam")
        away_score.text = intent.getStringExtra("intAwayScore")
        away_goals.text = intent.getStringExtra("strAwayGoalDetails")
        away_red_card.text = intent.getStringExtra("strAwayRedCards")
        away_yellow_card.text = intent.getStringExtra("strAwayYellowCards")
        away_goalkeeper.text = intent.getStringExtra("strAwayLineupGoalkeeper")
        away_deffense.text = intent.getStringExtra("strAwayLineupDefense")
        away_midfield.text = intent.getStringExtra("strAwayLineupMidfield")
        away_forwards.text = intent.getStringExtra("strAwayLineupForward")
        away_sub.text = intent.getStringExtra("strAwayLineupSubstitutes")


        checkFavorit()
    }

    private fun getHomeLogo(id: String) {
        teamPresenter.getEmblemHome(id)
    }

    private fun getAwayLogo(id: String) {
        teamPresenter.getEmblemAway(id)
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                        Favorite.TABLE_FAVORITE,
                        Favorite.FAVORITE_ID to idEvent,
                        Favorite.FAVORITE_FROM to from
                )
            }

            isFavorite = true
            menuItem?.icon = ContextCompat.getDrawable(this, setFavorite())

            snackbar(coordinatorLayout, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(coordinatorLayout, e.localizedMessage).show()
        }
    }

    private fun deleteFavorit(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, Favorite.FAVORITE_ID + " = {idEvent}", "idEvent" to idEvent)
            }

            isFavorite = false
            menuItem?.icon = ContextCompat.getDrawable(this, setFavorite())

            snackbar(coordinatorLayout, "Delete from favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(coordinatorLayout, e.localizedMessage).show()
        }
    }

    private fun checkFavorit() {
        try {
            database.use {
                select(Favorite.TABLE_FAVORITE,Favorite.FAVORITE_ID)
                        .whereArgs(Favorite.FAVORITE_ID + "= {idEvent} " + Favorite.FAVORITE_FROM + "= " + from, "idEvent" to idEvent)
                        .exec {
                            parseOpt(object: MapRowParser<Boolean>{
                                override fun parseRow(columns: Map<String, Any?>): Boolean {
                                    if (columnCount > 0){
                                        isFavorite = true
                                    }

                                    return isFavorite
                                }
                            })
                        }
            }
        } catch (e: SQLiteConstraintException){
            snackbar(coordinatorLayout, e.localizedMessage).show()
        }
    }

    private fun setFavorite() : Int {
        if (isFavorite)
            return R.drawable.ic_favorite
        else
            return  R.drawable.ic_favorite_border

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorit_menu, menu)
        menuItem = menu?.findItem(R.id.add_to_favorite)
        menuItem?.icon = ContextCompat.getDrawable(this, setFavorite())

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_to_favorite -> {
                if (isFavorite)
                    deleteFavorit()
                else
                    addToFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
