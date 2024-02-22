package org.jpaassociationmapping.service;

import lombok.RequiredArgsConstructor;
import org.jpaassociationmapping.domain.Team;
import org.jpaassociationmapping.infrastructure.persistence.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepo;

    public Team save(Team team) {
        return teamRepo.save(team);
    }
    public List<Team> findAll() {
        return teamRepo.findAll();
    }

    public Team findTop1ById(String id) {
        return teamRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 팀이 존재하지 않습니다.")
        );
    }

    public void delete(Team team) {
        teamRepo.delete(team);
    }
}
