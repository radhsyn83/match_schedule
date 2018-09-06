package com.fathurradhy.matchschedule.domain.presenter

import com.fathurradhy.matchschedule.domain.model.TeamModel
import com.fathurradhy.matchschedule.domain.view.TeamView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamImplsTest {

    @Mock
    private lateinit var teamView: TeamView

    @Mock
    private lateinit var teamPresenter: TeamPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        teamPresenter = TeamImpls(teamView)
    }

    @Test
    fun loadTeamDetail() {
        val teams = TeamModel(arrayListOf())

        val teamId = "133613"

        teamPresenter.loadTeamDetail(teamId)

        Mockito.verify(teamView).onSuccess(teams)
    }
}