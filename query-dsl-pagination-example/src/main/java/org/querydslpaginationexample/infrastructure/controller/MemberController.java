package org.querydslpaginationexample.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.querydslpaginationexample.infrastructure.MemberRepository;
import org.querydslpaginationexample.infrastructure.model.MemberSearchCondition;
import org.querydslpaginationexample.infrastructure.model.MemberTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/v1/members")
    public List<MemberTeamDto>searchMemberV1(MemberSearchCondition memberSearchCondition) {
        return memberRepository.search(memberSearchCondition);
    }

    @GetMapping("/v2/members")
    public Page<MemberTeamDto> searchMemberV2(MemberSearchCondition memberSearchCondition, Pageable pageable) {
        return memberRepository.searchPage(memberSearchCondition, pageable);
    }
}
