package org.i18ntest.jpajsonperformancemeasurement.domain;

import jakarta.persistence.*;
import lombok.*;
import org.i18ntest.jpajsonperformancemeasurement.common.JsonLongBooleanMapConverter;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name = "json_post")
public class JSONPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @Convert(converter = JsonLongBooleanMapConverter.class)
    private ConcurrentHashMap<Long, Boolean> votes;

    public ConcurrentHashMap<Long, Boolean> getVotes() {
        if (votes == null) {
            return new ConcurrentHashMap<>();
        }
        return this.votes;
    }

    public void setVotes(ConcurrentHashMap<Long, Boolean> newVotes) {
        this.votes = newVotes;
    }
}
