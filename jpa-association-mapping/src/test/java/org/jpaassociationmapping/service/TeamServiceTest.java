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

    @Test
    void MEMBER_영속성전이_테스트() {
        // given
        Team teamA = Team.builder().id("팀A").name("팀A").build();
        // 현재 시간을 String으로 변환
        String time = String.valueOf(System.currentTimeMillis());
        // 어제 시간을 String으로 변환
        String yesterday = String.valueOf(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        Member m1 = Member.builder().username(time).id(time).build();
        Member m2 = Member.builder().username(yesterday).id(yesterday).build();
        m1.setTeam(teamA);
        m2.setTeam(teamA);

        // when
        teamService.save(teamA);

        // then
        assertEquals(m1.getUsername(), memberService.findByUserName(m1.getUsername()).getUsername());
        assertEquals(m2.getUsername(), memberService.findByUserName(m2.getUsername()).getUsername());
    }
}