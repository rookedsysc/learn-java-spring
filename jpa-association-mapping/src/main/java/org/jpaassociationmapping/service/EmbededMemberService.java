package org.jpaassociationmapping.service;

import lombok.RequiredArgsConstructor;
import org.jpaassociationmapping.domain.EmbededMember;
import org.jpaassociationmapping.infrastructure.persistence.EmbededMemberRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmbededMemberService {
    private final EmbededMemberRepository embededMemberRepository;
    public EmbededMember save(EmbededMember embededMember) {
        return embededMemberRepository.save(embededMember);
    }

    public EmbededMember findById(Long id) {
        return embededMemberRepository.findById(id).orElseThrow(
            () -> new RuntimeException("EmbededMember not found")
        );
    }
}
