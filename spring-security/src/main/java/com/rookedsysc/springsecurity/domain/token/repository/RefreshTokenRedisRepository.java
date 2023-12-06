package com.rookedsysc.springsecurity.domain.token.repository;

import com.rookedsysc.springsecurity.domain.token.model.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, String> {
  Optional<RefreshToken> findRefreshTokenByRefreshToken(String refreshToken);
}
