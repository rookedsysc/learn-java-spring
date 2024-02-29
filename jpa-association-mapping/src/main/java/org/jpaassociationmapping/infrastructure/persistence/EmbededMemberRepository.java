package org.jpaassociationmapping.infrastructure.persistence;

import org.jpaassociationmapping.domain.EmbededMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmbededMemberRepository extends JpaRepository<EmbededMember, Long> {
}
