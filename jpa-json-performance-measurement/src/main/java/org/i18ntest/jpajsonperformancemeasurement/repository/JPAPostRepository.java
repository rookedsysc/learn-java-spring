package org.i18ntest.jpajsonperformancemeasurement.repository;

import org.i18ntest.jpajsonperformancemeasurement.domain.JPAPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JPAPostRepository extends JpaRepository<JPAPost, Long> {
    @Query("select p from jpa_post p left join fetch p.vote")
    public List<JPAPost> findAllFetch();
}
