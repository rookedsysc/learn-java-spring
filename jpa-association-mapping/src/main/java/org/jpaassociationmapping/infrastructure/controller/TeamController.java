package org.jpaassociationmapping.infrastructure.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jpaassociationmapping.domain.Member;
import org.jpaassociationmapping.domain.Team;
import org.jpaassociationmapping.infrastructure.controller.dto.TeamSaveDto;
import org.jpaassociationmapping.service.TeamService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @PostMapping("/save")
    public Team save(
            @RequestBody TeamSaveDto dto
    ) {
        Team team = dto.toEntity();
        // 현재 시간을 String으로 변환
        String time = String.valueOf(System.currentTimeMillis());
        // 어제 시간을 String으로 변환
        String yesterday = String.valueOf(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        Member m1 = Member.builder().username(time).id(time).build();

        Member m2 = Member.builder().username(yesterday).id(yesterday).build();
        m1.setTeam(team);
        m2.setTeam(team);
        return teamService.save(team);
    }

}
