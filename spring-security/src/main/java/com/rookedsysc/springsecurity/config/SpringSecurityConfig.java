package com.rookedsysc.springsecurity.config;

import com.rookedsysc.springsecurity.domain.token.helper.JwtTokenHelper;
import com.rookedsysc.springsecurity.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

  private final UserService userService;
  private final JwtTokenHelper jwtTokenHelper;
  // Spring.bean.factory.annotation.Value
  @Value("${token.secret.key}")
  private String secretKey;

  private List<String> SWAGGER = List.of("/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs", "/v3/api-docs/**");
  private List<String> OPENAPI = List.of("/open-api/**");


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    // Username Password 전에 JwtFilter를 먼저 확인하도록 설정함
    httpSecurity.addFilterBefore(new JwtFilter(userService, secretKey, jwtTokenHelper), UsernamePasswordAuthenticationFilter.class);
    httpSecurity.csrf(csrf -> csrf.disable()).
        authorizeHttpRequests(it -> {
          // static Resource에 대해서는 모두 허가함
          it.requestMatchers(
                  PathRequest.toStaticResources()
                      .atCommonLocations()
              )
              .permitAll().
              requestMatchers(SWAGGER.toArray(new String[0]))
              .permitAll().
              requestMatchers(OPENAPI.toArray(new String[0]))
              .permitAll().
              anyRequest()
              .authenticated();
        })
        .httpBasic(Customizer.withDefaults())
        .formLogin(Customizer.withDefaults());
    return httpSecurity.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
