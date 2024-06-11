package org.i18ntest.jpajsonperformancemeasurement.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    public List<Vote> getVote() {
        if(this.vote == null)  {
            return new ArrayList<>();
        }
        return this.vote;
    }
}
