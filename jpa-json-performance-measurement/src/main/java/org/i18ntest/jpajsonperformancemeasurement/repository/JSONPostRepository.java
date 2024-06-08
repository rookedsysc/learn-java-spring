package org.i18ntest.jpajsonperformancemeasurement.repository;

import org.i18ntest.jpajsonperformancemeasurement.domain.JSONPost;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JSONPostRepository extends JpaRepository<JSONPost, Long> {
}
