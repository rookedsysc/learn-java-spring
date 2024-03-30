package org.querydslpaginationexample.infrastructure.ifs;

import org.querydslpaginationexample.entity.Member;
import org.querydslpaginationexample.infrastructure.model.MemberSearchCondition;
import org.querydslpaginationexample.infrastructure.model.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCondition condition);

    List<Member> searchAllMembers();
}
