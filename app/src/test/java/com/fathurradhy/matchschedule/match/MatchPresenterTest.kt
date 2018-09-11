package com.fathurradhy.matchschedule.match

import com.fathurradhy.matchschedule.entity.MatchResponse
import com.fathurradhy.matchschedule.entity.TeamResponse
import com.fathurradhy.matchschedule.test.repository.RetrofitRepository
import com.fathurradhy.matchschedule.test.repository.MatchRepositoryCallback
import com.fathurradhy.matchschedule.test.repository.TeamRepositoryCallback
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
    private lateinit var matchView: MatchView

    @Mock
    private lateinit var teamView: TeamView

    @Mock
    private lateinit var retrofitRepository: RetrofitRepository

    @Mock
    private lateinit var matchResponse: MatchResponse

    @Mock
    private lateinit var teamResponse: TeamResponse

    private lateinit var matchPresenter: MatchPresenter
    private lateinit var teamPresenter: TeamPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        matchPresenter = MatchPresenter(matchView, retrofitRepository)
        teamPresenter = TeamPresenter(teamView, retrofitRepository)
    }

    /*PREVIOUS MATCH*/

    @Test
    fun getPrevMatchSuccess() {
        val id = "4328"

        matchPresenter.getPrevMatch(id)

        argumentCaptor<MatchRepositoryCallback<MatchResponse?>>().apply {
            verify(retrofitRepository).getPrevMatch(eq(id), capture())
            firstValue.onDataLoaded(matchResponse)
        }

        Mockito.verify(matchView).onShowLoading()
        Mockito.verify(matchView).onDataLoaded(matchResponse)
        Mockito.verify(matchView).onHideLoading()
    }

    @Test
    fun getPrevMatchError() {
        matchPresenter.getPrevMatch("")
        argumentCaptor<MatchRepositoryCallback<MatchResponse?>>().apply {

            verify(retrofitRepository).getPrevMatch(eq(""), capture())
            firstValue.onDataError()
        }

        Mockito.verify(matchView).onShowLoading()
        Mockito.verify(matchView).onDataError()
        Mockito.verify(matchView).onHideLoading()
    }


    /*NEXT MATCH*/

    @Test
    fun getNextMatchSuccess() {
        val id = "4328"

        matchPresenter.getNextMatch(id)
        argumentCaptor<MatchRepositoryCallback<MatchResponse?>>().apply {
            verify(retrofitRepository).getNextMatch(eq(id), capture())
            firstValue.onDataLoaded(matchResponse)
        }

        Mockito.verify(matchView).onShowLoading()
        Mockito.verify(matchView).onDataLoaded(matchResponse)
        Mockito.verify(matchView).onHideLoading()
    }

    @Test
    fun getNextMatchError() {
        matchPresenter.getNextMatch("")

        argumentCaptor<MatchRepositoryCallback<MatchResponse?>>().apply {
            verify(retrofitRepository).getNextMatch(eq(""), capture())
            firstValue.onDataError()
        }

        Mockito.verify(matchView).onShowLoading()
        Mockito.verify(matchView).onDataError()
        Mockito.verify(matchView).onHideLoading()
    }

    /*MATCH DETAIL*/

    @Test
    fun getMatchDetailSuccess() {
        val id = "576511"

        matchPresenter.getDetailMatch(id)
        argumentCaptor<MatchRepositoryCallback<MatchResponse?>>().apply {
            verify(retrofitRepository).getDetailMatch(eq(id), capture())
            firstValue.onDataLoaded(matchResponse)
        }

        Mockito.verify(matchView).onShowLoading()
        Mockito.verify(matchView).onDataLoaded(matchResponse)
        Mockito.verify(matchView).onHideLoading()
    }

    @Test
    fun getMatchDetailError() {
        matchPresenter.getDetailMatch("")

        argumentCaptor<MatchRepositoryCallback<MatchResponse?>>().apply {
            verify(retrofitRepository).getDetailMatch(eq(""), capture())
            firstValue.onDataError()
        }

        Mockito.verify(matchView).onShowLoading()
        Mockito.verify(matchView).onDataError()
        Mockito.verify(matchView).onHideLoading()
    }

    /*GET EMBLEM*/

    @Test
    fun getEmblemSuccess() {
        val id = "133612"
        val side = "home"

        teamPresenter.getEmblemHome(id)
        argumentCaptor<TeamRepositoryCallback<TeamResponse?>>().apply {
            verify(retrofitRepository).getEmblemHome(eq(id), capture())
            firstValue.onDataLoaded(teamResponse, side)
        }

        Mockito.verify(teamView).onShowLoading()
        Mockito.verify(teamView).onDataLoaded(teamResponse, side)
        Mockito.verify(teamView).onHideLoading()
    }

    @Test
    fun getEmblemError() {
        teamPresenter.getEmblemHome("")
        argumentCaptor<TeamRepositoryCallback<TeamResponse?>>().apply {
            verify(retrofitRepository).getEmblemHome(eq(""), capture())
            firstValue.onDataError()
        }

        Mockito.verify(teamView).onShowLoading()
        Mockito.verify(teamView).onDataError()
        Mockito.verify(teamView).onHideLoading()
    }
}