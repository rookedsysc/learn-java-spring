package org.jpaassociationmapping.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder @Getter
public class Member {
    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    @Column
    private String username;

    @ManyToOne @Setter
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}
