package org.jpaassociationmapping.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter
@Table(name = "TEAM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class Team {
    @Id
    @Column(name = "TEAM_ID")
    private String id;
    private String name;
}
