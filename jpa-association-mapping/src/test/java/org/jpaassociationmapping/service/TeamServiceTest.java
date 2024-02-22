package org.jpaassociationmapping.service;

import jakarta.transaction.Transactional;
import org.jpaassociationmapping.domain.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Test
    void 팀_저장_테스트() {
        // give
        Team team = Team.builder().id("팀A").name("teamA").build();

        // when
        Team savedTeam = teamService.save(team);

        // then
        assertEquals(team.getName(), savedTeam.getName());
        assertEquals(team.getId(), savedTeam.getId());
    }

    @Test
    void 팀_조회_테스트() {
        // give
        Team team = Team.builder().id("팀A").name("teamA").build();
        teamService.save(team);

        // when
        List<Team> foundTeam = teamService.findAll();

        // then
        assertEquals(1, foundTeam.size());
    }
}