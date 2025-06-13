package com.ironhack.products_inventory.security;

import com.ironhack.products_inventory.security.filters.CustomAuthenticationFilter;
import com.ironhack.products_inventory.security.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity // Indica que esta clase configura la seguridad web con Spring Security
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    private final AuthenticationManagerBuilder authManagerBuilder;

    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authManagerBuilder.getOrBuild());

        customAuthenticationFilter.setFilterProcessesUrl("/api/login");  // <---- si quiero que mi login sea otro lo modifico aquí TAMBIÉN

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);

        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/api/login/**").permitAll()
                .requestMatchers("/products/**").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(POST,"/orders/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers("/orders/**").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(GET, "/api/users").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(POST, "/api/users").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(POST, "/api/roles").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(POST, "/api/roles/add-to-user").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated());


        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
