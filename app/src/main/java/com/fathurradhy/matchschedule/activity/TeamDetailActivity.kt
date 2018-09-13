package com.fathurradhy.matchschedule.activity

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import com.afollestad.materialdialogs.MaterialDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fathurradhy.matchschedule.R
import com.fathurradhy.matchschedule.adapter.PlayerAdapter
import com.fathurradhy.matchschedule.mvp.model.PlayerItem
import com.fathurradhy.matchschedule.mvp.model.PlayerResponse
import com.fathurradhy.matchschedule.mvp.presenter.PlayerPresenter
import com.fathurradhy.matchschedule.test.repository.PlayerView
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import com.fathurradhy.matchschedule.utils.CustomProgressDialog
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.toast

class TeamDetailActivity : AppCompatActivity() {

    private var mMaterialDialog: MaterialDialog? = null
    private var menuItem: MenuItem? = null
    private var isFavorite: Boolean = false
    private lateinit var playerItem: List<PlayerItem>

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        CustomProgressDialog.showDialog(this)

        title = intent.getStringExtra("strTeam")

        if (Build.VERSION.SDK_INT >= 21) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = resources.getColor(R.color.colorTeamDark)
        }

        Glide.with(this)
                .load(intent.getStringExtra("strTeamBadge"))
                .apply(RequestOptions()
                        .placeholder(R.drawable.placeholder))
                .into(logo)

        Glide.with(this)
                .load(intent.getStringExtra("strTeamFanart1"))
                .apply(RequestOptions()
                        .placeholder(R.drawable.placeholder))
                .into(image)

        content.text = intent.getStringExtra("strDescriptionEN")
        channel.text = intent.getStringExtra("strSport")

        PlayerPresenter(object : PlayerView{
            override fun onDataLoaded(data: PlayerResponse?) {
                CustomProgressDialog.stopDialog()
                playerItem = data?.player!!
            }

            override fun onDataError() {}
        }, RetrofitRepository()).getPlayerByTeam(intent.getStringExtra("idTeam"))
    }

    private fun playerModal() {
        mMaterialDialog = MaterialDialog.Builder(this)
                .title(resources.getString(R.string.player))
                .titleColor(resources.getColor(R.color.colorTeamDark))
                .customView(R.layout.dialog_player, false)
                .negativeColor(resources.getColor(R.color.colorTeamDark))
                .negativeText(resources.getString(R.string.close))
                .onNegative { dialog, which ->
                    mMaterialDialog!!.dismiss()
                }
                .show()

        val view = mMaterialDialog!!.view
        val player_rv = view.findViewById<RecyclerView>(R.id.player_rv)
        player_rv.layoutManager = LinearLayoutManager(this)
        player_rv.adapter = PlayerAdapter(this, playerItem, object : PlayerAdapter.Listener{
            override fun onMatchClick(data: PlayerItem) {
                data.strPlayer?.let { toast(it) }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.team_menu, menu)
//        menuItem = menu?.findItem(R.id.add_to_favorite)
//        menuItem?.icon = ContextCompat.getDrawable(this, setFavorite())

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.player -> {
                playerModal()

                true
            }

            R.id.btn_favorite -> {
//                if (isFavorite)
//                    deleteFavorit()
//                else
//                    addToFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
