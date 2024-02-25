package org.jpaassociationmapping.infrastructure.persistence;

import org.jpaassociationmapping.domain.Member;
import org.jpaassociationmapping.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, String>{
    Optional<Team> findByName(String name);
}
