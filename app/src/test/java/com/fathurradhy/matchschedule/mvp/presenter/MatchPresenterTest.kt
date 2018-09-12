package com.fathurradhy.matchschedule.mvp.presenter

import com.fathurradhy.matchschedule.mvp.model.LeaguesResponse
import com.fathurradhy.matchschedule.mvp.model.MatchResponse
import com.fathurradhy.matchschedule.mvp.model.SearchResponse
import com.fathurradhy.matchschedule.mvp.model.TeamResponse
import com.fathurradhy.matchschedule.test.repository.LeaguesView
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import com.fathurradhy.matchschedule.test.repository.MatchView
import com.fathurradhy.matchschedule.test.repository.SearchView
import com.fathurradhy.matchschedule.test.repository.TeamView
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchPresenterTest {
    @Mock
    private lateinit var retrofitRepository: RetrofitRepository

    @Mock
    private lateinit var matchResponse: MatchResponse

    @Mock
    private lateinit var matchView: MatchView

    @Mock
    private lateinit var teamView: TeamView<TeamResponse?>

    @Mock
    private lateinit var leaguesView: LeaguesView

    @Mock
    private lateinit var searchView: SearchView

    @Mock
    private lateinit var teamResponse: TeamResponse

    @Mock
    private lateinit var searchResponse: SearchResponse

    @Mock
    private lateinit var leaguesResponse: LeaguesResponse

    private lateinit var matchPresenter: MatchPresenter
    private lateinit var teamPresenter: TeamPresenter
    private lateinit var leaguesPresenter: LeaguesPresenter
    private lateinit var searchPresenter: SearchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        matchPresenter = MatchPresenter(matchView, retrofitRepository)
        teamPresenter = TeamPresenter(teamView, retrofitRepository)
        leaguesPresenter = LeaguesPresenter(leaguesView, retrofitRepository)
        searchPresenter = SearchPresenter(searchView, retrofitRepository)
    }

    /*PREVIOUS MATCH*/

    @Test
    fun getPrevMatchSuccess() {
        val id = "4328"

        matchPresenter.getPrevMatch(id)

        argumentCaptor<MatchView>().apply {
            verify(retrofitRepository).getPrevMatch(eq(id), capture())
            firstValue.onDataLoaded(matchResponse)
        }

        Mockito.verify(matchView).onDataLoaded(matchResponse)
    }

    @Test
    fun getPrevMatchError() {
        matchPresenter.getPrevMatch("")
        argumentCaptor<MatchView>().apply {

            verify(retrofitRepository).getPrevMatch(eq(""), capture())
            firstValue.onDataError()
        }

        Mockito.verify(matchView).onDataError()
    }

    /*NEXT MATCH*/

    @Test
    fun getNextMatchSuccess() {
        val id = "4328"

        matchPresenter.getNextMatch(id)
        argumentCaptor<MatchView>().apply {
            verify(retrofitRepository).getNextMatch(eq(id), capture())
            firstValue.onDataLoaded(matchResponse)
        }

        Mockito.verify(matchView).onDataLoaded(matchResponse)
    }

    @Test
    fun getNextMatchError() {
        matchPresenter.getNextMatch("")

        argumentCaptor<MatchView>().apply {
            verify(retrofitRepository).getNextMatch(eq(""), capture())
            firstValue.onDataError()
        }

        Mockito.verify(matchView).onDataError()
    }

    /*MATCH DETAIL*/
    @Test
    fun getMatchDetailSuccess() {
        val id = "576511"

        matchPresenter.getDetailMatch(id)
        argumentCaptor<MatchView>().apply {
            verify(retrofitRepository).getDetailMatch(eq(id), capture())
            firstValue.onDataLoaded(matchResponse)
        }

        Mockito.verify(matchView).onDataLoaded(matchResponse)
    }

    @Test
    fun getMatchDetailError() {
        matchPresenter.getDetailMatch("")

        argumentCaptor<MatchView>().apply {
            verify(retrofitRepository).getDetailMatch(eq(""), capture())
            firstValue.onDataError()
        }

        Mockito.verify(matchView).onDataError()
    }

    /*GET EMBLEM*/

    @Test
    fun getEmblemSuccess() {
        val id = "133612"
        val side = "home"

        teamPresenter.getEmblemHome(id)
        argumentCaptor<TeamView<TeamResponse?>>().apply {
            verify(retrofitRepository).getEmblemHome(eq(id), capture())
            firstValue.onDataLoaded(teamResponse, side)
        }

        Mockito.verify(teamView).onDataLoaded(teamResponse, side)
    }

    @Test
    fun getEmblemError() {
        teamPresenter.getEmblemHome("")
        argumentCaptor<TeamView<TeamResponse?>>().apply {
            verify(retrofitRepository).getEmblemHome(eq(""), capture())
            firstValue.onDataError()
        }

        Mockito.verify(teamView).onDataError()
    }

    /*GET LEAGUES*/
    @Test
    fun getLeaguesSuccess() {
        leaguesPresenter.getLeagues()
        argumentCaptor<LeaguesView>().apply {
            verify(retrofitRepository).getLeagues(capture())
            firstValue.onDataLoaded(leaguesResponse)
        }

        Mockito.verify(leaguesView).onDataLoaded(leaguesResponse)
    }

    /*GET EMBLEM*/

    @Test
    fun getSearchSuccess() {
        val e = "barcelona"

        searchPresenter.getSearchTeam(e)
        argumentCaptor<SearchView>().apply {
            verify(retrofitRepository).getSearchTeam(eq(e), capture())
            firstValue.onDataLoaded(searchResponse)
        }

        Mockito.verify(searchView).onDataLoaded(searchResponse)
    }

    @Test
    fun getLeaguesError() {
        val e = "bancilona"

        searchPresenter.getSearchTeam(e)
        argumentCaptor<SearchView>().apply {
            verify(retrofitRepository).getSearchTeam(eq(e), capture())
            firstValue.onDataError()
        }

        Mockito.verify(searchView).onDataError()
    }

}