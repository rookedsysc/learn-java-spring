package org.querydslpaginationexample.infrastructure;

import org.querydslpaginationexample.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long>{
}
