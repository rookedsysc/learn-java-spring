package org.i18ntest.jpajsonperformancemeasurement.domain;

import jakarta.persistence.*;

@Entity(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean vote;
    @ManyToOne
    @JoinColumn(nullable = false, name = "post_id")
    private JPAPost post;
}
