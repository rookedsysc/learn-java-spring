package org.i18ntest.jpajsonperformancemeasurement.repository;

import org.i18ntest.jpajsonperformancemeasurement.domain.JPAPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAPostRepository extends JpaRepository<JPAPost, Long> {
}
