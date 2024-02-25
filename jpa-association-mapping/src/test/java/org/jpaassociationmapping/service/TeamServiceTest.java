package org.jpaassociationmapping.service;

import jakarta.transaction.Transactional;
import org.jpaassociationmapping.domain.Member;
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

    @Autowired
    private MemberService memberService;


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

    @Test
    void 팀_조회_By_멤버() {
        // given
        Team teamA = Team.builder().id("팀A").name("팀A").build();
        Team savedTeam = teamService.save(teamA);
        Member member = Member.builder().id("memberA").username("memberA").build();
        member.setTeam(savedTeam);
        Member savedMember = memberService.save(member);

        // when
        Team searchedTeam = teamService.findByName(savedTeam.getName());

        // then
        assertEquals(savedMember.getUsername(), searchedTeam.getMembers().get(0).getUsername());
    }
}