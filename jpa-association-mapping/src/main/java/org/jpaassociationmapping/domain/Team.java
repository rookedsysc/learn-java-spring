package org.jpaassociationmapping.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "TEAM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class Team {
    @Id
    @Column(name = "TEAM_ID")
    private String id;
    private String name;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Member> members = new ArrayList<Member>();
}
