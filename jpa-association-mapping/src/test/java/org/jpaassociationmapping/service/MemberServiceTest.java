package org.jpaassociationmapping.service;

import jakarta.transaction.Transactional;
import org.jpaassociationmapping.domain.Member;
import org.jpaassociationmapping.domain.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TeamService teamService;

    @Test
    void 멤버_저장_테스트() {
        // given
        Team teamA = Team.builder().id("팀A").name("팀A").build();
        teamService.save(teamA);
        Member member = Member.builder().id("memberA").username("memberA").team(teamA).build();

        // when
        Member savedMember = memberService.save(member);

        // then
        assertEquals(member.getUsername(), savedMember.getUsername());
        assertEquals(member.getId(), savedMember.getId());
    }

    @Test
    void 멤버_팀_변경_테스트() {
        // given
        Team teamA = Team.builder().id("팀A").name("팀A").build();
        teamService.save(teamA);
        Team teamB = Team.builder().id("팀B").name("팀B").build();
        teamService.save(teamB);
        Member member = Member.builder().id("memberA").username("memberA").team(teamA).build();
        Member savedMember = memberService.save(member);

        // when
        Member member2 = memberService.findByUserName(member.getUsername());
        member2.setTeam(teamB);
        Member savedMember2 = memberService.save(member2);

        // then
        assertEquals(savedMember2.getUsername(), member2.getUsername());
        assertEquals(savedMember2.getTeam().getName(), member2.getTeam().getName());
        assertNotEquals(member.getTeam().getName(), savedMember2.getTeam().getName());
    }

    @Test
    void 팀_삭제시_멤버_조회() {
        // given
        Team teamA = Team.builder().id("팀A").name("팀A").build();
        teamService.save(teamA);
        Member member = Member.builder().id("memberA").username("memberA").team(teamA).build();
        Member savedMember = memberService.save(member);

        // when
        teamService.delete(teamA);

        // then
        assert
        Member member2 = memberService.findByUserName(member.getUsername());
        System.out.println(member2);
    }
}