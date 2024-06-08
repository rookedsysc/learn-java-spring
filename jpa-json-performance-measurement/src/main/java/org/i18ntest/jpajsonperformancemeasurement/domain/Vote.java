package org.i18ntest.jpajsonperformancemeasurement.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean vote;
    @Getter
    @ManyToOne
    @JoinColumn(nullable = false, name = "post_id")
    private JPAPost post;
}
