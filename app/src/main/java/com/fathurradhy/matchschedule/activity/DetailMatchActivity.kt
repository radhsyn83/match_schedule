package com.fathurradhy.matchschedule.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.domain.presenter.TeamImpls
import com.fathurradhy.matchschedule.domain.view.TeamView
import com.fathurradhy.matchschedule.utils.CustomProgressDialog
import com.fathurradhy.matchschedule.utils.DateUtils
import com.fathurradhy.matchschedule.utils.Favorite
import com.fathurradhy.matchschedule.utils.database
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.db.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast

class DetailMatchActivity : AppCompatActivity() {

    private var menuItem: MenuItem? = null
    private var isFavorite: Boolean = false
    private lateinit var idEvent: String

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true)

        idEvent = intent.getStringExtra("idEvent")

        CustomProgressDialog.showDialog(this)

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
        TeamImpls(object : TeamView{
            override fun onSuccess(teamImg: String) {
                Glide.with(this@DetailMatchActivity)
                        .load(teamImg)
                        .apply(RequestOptions()
                                .placeholder(R.drawable.placeholder))
                        .into(home_logo)
                home_logo.visibility = View.VISIBLE
            }
        }).loadTeamDetail(id)
    }

    private fun getAwayLogo(id: String) {
        TeamImpls(object : TeamView{
            override fun onSuccess(teamImg: String) {
                CustomProgressDialog.stopDialog()
                Glide.with(this@DetailMatchActivity)
                        .load(teamImg)
                        .apply(RequestOptions()
                                .placeholder(R.drawable.placeholder))
                        .into(away_logo)
                away_logo.visibility = View.VISIBLE
            }
        }).loadTeamDetail(id)
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                        Favorite.TABLE_FAVORITE,
                        Favorite.MATCH_ID to idEvent
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
                delete(Favorite.TABLE_FAVORITE, Favorite.MATCH_ID + " = {idEvent}", "idEvent" to idEvent)
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
                select(Favorite.TABLE_FAVORITE,Favorite.MATCH_ID)
                        .whereArgs(Favorite.MATCH_ID + "= {idEvent}", "idEvent" to idEvent)
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
