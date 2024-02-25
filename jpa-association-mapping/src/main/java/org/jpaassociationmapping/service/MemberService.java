package org.jpaassociationmapping.service;

import lombok.RequiredArgsConstructor;
import org.jpaassociationmapping.domain.Member;
import org.jpaassociationmapping.infrastructure.persistence.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepo;

    public Member save(Member member) {
        return memberRepo.save(member);
    }

    public Member findByUserName(String userName) {
        return memberRepo.findTop1ByUsername(userName);
    }

    public void deleteAll() {
        memberRepo.deleteAll();
    }
}
