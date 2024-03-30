package org.querydslpaginationexample.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.querydslpaginationexample.infrastructure.MemberRepository;
import org.querydslpaginationexample.infrastructure.model.MemberSearchCondition;
import org.querydslpaginationexample.infrastructure.model.MemberTeamDto;
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
}
