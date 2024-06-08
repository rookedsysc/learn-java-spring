package org.i18ntest.jpajsonperformancemeasurement.domain;

import jakarta.persistence.*;
import lombok.*;
import org.i18ntest.jpajsonperformancemeasurement.common.JsonLongBooleanMapConverter;

import java.util.HashMap;

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
    private HashMap<Long, Boolean> votes;

    public HashMap<Long, Boolean> getVotes() {
        if (votes == null) {
            return new HashMap<>();
        }
        return this.votes;
    }

    public void setVotes(HashMap<Long, Boolean> newVotes) {
        this.votes = newVotes;
    }
}
