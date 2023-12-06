package com.rookedsysc.springsecurity.config;

import com.rookedsysc.springsecurity.domain.token.helper.JwtTokenHelper;
import com.rookedsysc.springsecurity.domain.user.model.UserRole;
import com.rookedsysc.springsecurity.domain.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
  private final UserService userService;
  private final String secretKey;
  private final JwtTokenHelper jwtTokenHelper;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    final String authorization = request.getHeader("Authorization");
    if(authorization == null || !authorization.startsWith("Bearer")) {
      filterChain.doFilter(request, response);
      return;
    }
    log.info("authorization: {}", authorization);

    // 토큰 꺼내기 + 검증
    String token = authorization.split(" ")[1];
    jwtTokenHelper.validationTokenWithThrow(token);

    // userName 토큰에서 꺼내기
    String email = JwtTokenHelper.getUserEmail(token, secretKey);

    // 권한 부여
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null, List.of(new SimpleGrantedAuthority(UserRole.USER.name())));

    // Detail을 넣어준다.
    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    filterChain.doFilter(request, response);
  }
}
