package org.i18ntest.jpajsonperformancemeasurement.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "jpa_post")
public class JPAPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private List<Vote> vote;
}
