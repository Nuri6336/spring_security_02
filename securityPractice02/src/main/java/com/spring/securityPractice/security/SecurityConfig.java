package com.spring.securityPractice.security;

import com.spring.securityPractice.constants.AppConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,AuthenticationManager authenticationManager)
            throws Exception {
        http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->{
                   auth
                           .requestMatchers(HttpMethod.POST,AppConstants.SIGN_IN,AppConstants.SIGN_UP).permitAll()
                           .requestMatchers(HttpMethod.GET,"/users/hello").hasRole("USER")
                           .requestMatchers(HttpMethod.GET,"/users/hello2").hasRole("USER")
                           .requestMatchers(HttpMethod.GET,"/users/hello3").hasRole("ADMIN")
                           .requestMatchers(HttpMethod.GET,"/users/hello4").hasRole("STUDENT")
                           .requestMatchers(HttpMethod.GET,"/users/hello5").hasRole("STUDENT")
                           .anyRequest().authenticated();
                })
                .addFilter(new CustomAuthenticationFilter(authenticationManager))
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }

}
