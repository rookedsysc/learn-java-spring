package org.jpaassociationmapping.infrastructure.persistence;

import org.jpaassociationmapping.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String>{
    public Member findTop1ByUsername(String username);
}
