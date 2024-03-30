package org.querydslpaginationexample.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.error.AssertJMultipleFailuresError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.querydslpaginationexample.entity.Member;
import org.querydslpaginationexample.entity.Team;
import org.querydslpaginationexample.infrastructure.model.MemberSearchCondition;
import org.querydslpaginationexample.infrastructure.model.MemberTeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @Test
    public void basicTest() {
        Member member = new Member("member1", 10);
        memberRepository.save(member);

        Member findMember = memberRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

        List<Member> result1 = memberRepository.findAll();
        assertThat(result1).containsExactly(member);

        List<Member> result2 = memberRepository.findByUsername("member1");
        assertThat(result2).containsExactly(member);
    }

    @Test
    @DisplayName("커스텀 레포지토리 동작 테스트 추가")
    void searchTest() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10);
        member1.changeTeam(teamA);
        Member member2 = new Member("member2", 20);
        member2.changeTeam(teamA);
        Member member3 = new Member("member3", 30);
        member3.changeTeam(teamB);
        Member member4 = new Member("member4", 40);
        member4.changeTeam(teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        MemberSearchCondition memberSearchCondition = new MemberSearchCondition();
        memberSearchCondition.setUsername("member4");
        memberSearchCondition.setAgeGoe(35);
        memberSearchCondition.setAgeLoe(40);
        memberSearchCondition.setTeamName(teamB.getName());

        List<MemberTeamDto> result = memberRepository.search(memberSearchCondition);
        List<Member> members = memberRepository.findAll();

        assertThat(result).extracting("username").containsExactly("member4");
    }

    @Test
    @DisplayName("cascade 옵션을 사용한 간략화")
    void searchTest2() {
        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 20);
        Member member3 = new Member("member3", 30);
        Member member4 = new Member("member4", 40);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        teamA.addMembers(List.of(member1, member2));
        teamB.addMembers(List.of(member3, member4));
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        MemberSearchCondition memberSearchCondition = new MemberSearchCondition();
        memberSearchCondition.setUsername("member4");
        memberSearchCondition.setAgeGoe(35);
        memberSearchCondition.setAgeLoe(40);
        memberSearchCondition.setTeamName(teamB.getName());

        List<MemberTeamDto> result = memberRepository.search(memberSearchCondition);

        assertThat(result).extracting("username").containsExactly("member4");
    }

    @Test
    @DisplayName("조인 Error가 발생함")
    void searchErrorTest() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        MemberSearchCondition memberSearchCondition = new MemberSearchCondition();
        memberSearchCondition.setUsername("member4");
        memberSearchCondition.setAgeGoe(35);
        memberSearchCondition.setAgeLoe(40);
        memberSearchCondition.setTeamName(teamB.getName());

        List<Member> members = memberRepository.findAll();
        List<MemberTeamDto> result = memberRepository.search(memberSearchCondition);

        assertThrows(AssertionFailedError.class, () -> {
            assertThat(result).extracting("username").containsExactly("member4");
        });
    }
}