package com.rookedsysc.springsecurity.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenError implements ErrorCodeIfs {
  INVALID_TOKEN(400, 2400, "유효하지 않은 토큰"),
  EXPIRED_TOKEN(400, 2401, "만료된 토큰"),
  TOKEN_EXCEPTION(400, 2402, "토큰 예외 발생"),
  AUTHROIZATION_HEADER_NOT_FOUND(400, 2403, "Authorization Header가 존재하지 않습니다.");

  private final int httpStatusCode;
  private final int errorCode;
  private final String description;
}