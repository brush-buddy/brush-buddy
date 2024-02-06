package com.a205.brushbuddy.auth.config;

import com.a205.brushbuddy.auth.jwt.CustomJwtAuthenticationEntryPoint;
import com.a205.brushbuddy.auth.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomJwtAuthenticationEntryPoint customJwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // CSRF 보호를 비활성화
                .formLogin(AbstractHttpConfigurer::disable) // 폼 기반 로그인을 비활성화한다.
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ) // 세션 기반 인증을 사용하지 않는다.
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(customJwtAuthenticationEntryPoint)) // 인증 실패 시 customJwtAuthenticationEntryPoint에서 처리
                .authorizeHttpRequests(authorizeHttpRequests -> // 설정한 url은 인증없이 접근 가능하다.
                        authorizeHttpRequests
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/auth/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/error")).permitAll()
                                //swagger 허용
                                .requestMatchers("/swagger-ui/**","/swagger-resources/**","/v3/api-docs/**").permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/oauth/token")).permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/v1/auth/refresh").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/auth/logout").permitAll()
                                .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)  // Http 요청이 UsernamePasswordAuthenticationFilter 전에 JwtAuthenticationFilter
                .build();
    }
}