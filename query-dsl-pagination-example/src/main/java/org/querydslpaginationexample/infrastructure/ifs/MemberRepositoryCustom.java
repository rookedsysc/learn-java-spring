package org.querydslpaginationexample.infrastructure.ifs;

import org.querydslpaginationexample.entity.Member;
import org.querydslpaginationexample.infrastructure.model.MemberSearchCondition;
import org.querydslpaginationexample.infrastructure.model.MemberTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCondition condition);


    Page<MemberTeamDto> searchPage(MemberSearchCondition condition, Pageable pageable);
}
