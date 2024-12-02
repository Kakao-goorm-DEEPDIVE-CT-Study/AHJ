package com.example.mywebservice.config;

import com.example.mywebservice.user.service.UserDetailService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final UserDetailService userDetailService;

    //인증, 인가등의 보안은 모든 곳에 적용되는 것은 아니기에 상세설정이 필요
    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> web.ignoring()//보안이 필요없는 부분 설정
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers("/static/**");
    }

    //특정 http 요청에 대한 보안 구성
    //인증, 인가, 로그인, 로그아웃 관련 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/signup", "/user").permitAll()//해당 url은 모든 사용자가 인증 없이 접근 가능
                        .anyRequest().authenticated())//모든 요청에 대한 인증 요구
                .formLogin(form -> form// form기반 로그인 설정
                        .loginPage("/login")//로그인 페이지
                        .defaultSuccessUrl("/posts",true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/login")
                        .invalidateHttpSession(true))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http,
            BCryptPasswordEncoder passwordEncoder,
            UserDetailService userDetailService
    ) throws Exception{
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);//AuthenticationProvider가 사용자 정보를 위임할 UserDetailService선택
        authProvider.setPasswordEncoder(passwordEncoder);//비밀번호 인코더 설정
        return new ProviderManager(authProvider);// 인증 관리자 설정
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
