package org.jpaassociationmapping.infrastructure.controller.dto;

import lombok.*;
import org.jpaassociationmapping.domain.Team;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class TeamSaveDto {
    private String id;
    private String name;

    public Team toEntity() {
        return Team.builder().
                id(this.getId()).
                name(this.getName()).
                build();
    }
}
