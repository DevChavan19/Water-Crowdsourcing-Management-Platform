package com.watercrowdsourcing.department_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("******** configuring spring sec filter chain *******");

        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                        // Public endpoints
                        .requestMatchers("/users/login").permitAll()

                        // Admin-only: Full department management
                        .requestMatchers("/departments/**").hasRole("ADMIN")

                        // Any other request must be authenticated
                        .anyRequest().authenticated())
                // ✅ Register your JWT filter here
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

// @Configuration
// @EnableWebSecurity
// @EnableMethodSecurity
// @Slf4j
// public class SecurityConfiguration {
//
// private final JwtAuthenticationFilter jwtAuthenticationFilter;
//
// public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter)
// {
// this.jwtAuthenticationFilter = jwtAuthenticationFilter;
// }
//
// @Bean
// SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// log.info("******** configuring spring sec filter chain *******");
//
// http.csrf(csrf -> csrf.disable())
// .sessionManagement(session ->
// session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
// .authorizeHttpRequests(request -> request
// .requestMatchers("/users/signup", "/users/signup-admin",
// "/users/login").permitAll()
// .anyRequest().authenticated()
// )
// .addFilterBefore(jwtAuthenticationFilter,
// UsernamePasswordAuthenticationFilter.class);
//
// return http.build();
// }
//
// @Bean
// AuthenticationManager authenticationManager(AuthenticationConfiguration
// config) throws Exception {
// return config.getAuthenticationManager();
// }
// }

// package com.watercrowdsourcing.ums_service.security;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import
// org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import
// org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
//
// import lombok.extern.slf4j.Slf4j;
//
// @Configuration // To declare a java config class (equivalent to bean config
// xml file)
// @EnableWebSecurity // to enable spring security
// @EnableMethodSecurity // optional to add method level authorization rules
// @Slf4j
// public class SecurityConfiguration {
//
//
//
// /*
// * Configure Spring sec filter chain as a spring bean (@Bean) , to override
// the
// * spring sec defaults - Disable CSRF protection - Disable HttpSession -
// Disable
// * login / logout page generation (i.e disable form login) - Disable Basic
// * Authentication scheme. - Add authorization rules - swagger , sign in , sign
// * up , listing doctors.. - public end points - any other request -
// authenticate
// * Add HttpSecurity as the dependency - to build sec filter chain
// */
// @Bean
// SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// log.info("********configuring spring sec filter chain*******");
// // disable CSRF protection
// http.csrf(csrf -> csrf.disable())
// // disable HttpSession creation
// .sessionManagement(session ->
// session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
// // add url based authentication n authorization rules
// .authorizeHttpRequests(request ->
// request.anyRequest().permitAll());
// return http.build();
// }
//
// // Configure AuthManager as spring bean
// @Bean
// AuthenticationManager authenticationManager(AuthenticationConfiguration
// config) throws Exception {
// return config.getAuthenticationManager();
// }
// }
