package org.querydslpaginationexample.infrastructure;

import org.querydslpaginationexample.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public List<Member> findByUsername(String username);

}
