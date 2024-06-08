package org.i18ntest.jpajsonperformancemeasurement.repository;

import jakarta.persistence.Entity;
import org.i18ntest.jpajsonperformancemeasurement.domain.JSONPost;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JSONPostRepository extends JpaRepository<JSONPost, Long> {
}
