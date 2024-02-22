package org.jpaassociationmapping.infrastructure.persistence;

import org.jpaassociationmapping.domain.Member;
import org.jpaassociationmapping.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String>{
    Team findByName(String name);
}
