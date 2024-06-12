package org.i18ntest.jpajsonperformancemeasurement.repository;

import org.i18ntest.jpajsonperformancemeasurement.domain.JPAPost;
import org.i18ntest.jpajsonperformancemeasurement.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByPost(JPAPost post);
    Optional<Vote> findByMemberIdAndPostId(Long memberId, Long postId);
    Double countByPostIdAndVote(Long postId, Boolean voteValue);
}
