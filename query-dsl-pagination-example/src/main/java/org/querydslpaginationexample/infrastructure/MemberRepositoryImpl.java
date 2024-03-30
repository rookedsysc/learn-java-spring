package org.querydslpaginationexample.infrastructure;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.querydslpaginationexample.entity.Member;
import org.querydslpaginationexample.infrastructure.ifs.MemberRepositoryCustom;
import org.querydslpaginationexample.infrastructure.model.MemberSearchCondition;
import org.querydslpaginationexample.infrastructure.model.MemberTeamDto;
import org.querydslpaginationexample.infrastructure.model.QMemberTeamDto;

import java.util.List;

import static org.querydslpaginationexample.entity.QMember.member;
import static org.querydslpaginationexample.entity.QTeam.team;

public class MemberRepositoryImpl implements MemberRepositoryCustom {
    JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MemberTeamDto> search(MemberSearchCondition condition) {
        return queryFactory
                .select(new QMemberTeamDto(
                        member.id,
                        member.username,
                        member.age,
                        team.id,
                        team.name
                ))
                .from(member)
                .join(member.team, team)
                .where(
                        usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe())
                ).fetch();
    }

    private BooleanExpression usernameEq(String username) {
        BooleanExpression result = username == null ? null : member.username.eq(username);
        return result;
    }

    private BooleanExpression teamNameEq(String teamName) {
        BooleanExpression result = teamName == null ? null : team.name.eq(teamName);
        return result;
    }

    private BooleanExpression ageGoe(Integer ageGoe) {
        BooleanExpression result = ageGoe == null ? null : member.age.goe(ageGoe);
        return result;
    }

    private BooleanExpression ageLoe(Integer ageLoe) {
        BooleanExpression result = ageLoe == null ? null : member.age.loe(ageLoe);
        return result;
    }
}
