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

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity // Indica que esta clase configura la seguridad web con Spring Security
@RequiredArgsConstructor
public class SecurityConfig {

    // Servicio que Spring Security utiliza para cargar detalles de usuario desde la base de datos
    private final UserDetailsService userDetailsService;

    // Builder que nos permite construir el AuthenticationManager
    private final AuthenticationManagerBuilder authManagerBuilder;

    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Bean definition for SecurityFilterChain
     *
     * @param http the instance of HttpSecurity
     * @return an instance of the SecurityFilterChain
     * @throws Exception if there is an issue building the SecurityFilterChain
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authManagerBuilder.getOrBuild());

        customAuthenticationFilter.setFilterProcessesUrl("/api/login");  // <---- si quiero que mi login sea otro lo modifico aquí TAMBIÉN

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);

        // Configura los accesos públicos, privados y por rol
        // <---- aquí es donde añadiré mis endpoints, si son públicos o no y con qué roles puedo acceder a los privados
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/api/login/**").permitAll()
                .requestMatchers("api/greet").permitAll()
                .requestMatchers("api/greet/personal").hasAnyAuthority("ROLE_USER")
                .requestMatchers(GET, "/api/users").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(POST, "/api/users").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(POST, "/api/roles").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(POST, "/api/roles/add-to-user").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()); // aquí indicamos que cualquier otro endpoint tiene que tener autenticación (privado)

        // Añade el filtro de autenticación al objeto http de seguridad
        http.addFilter(customAuthenticationFilter);

        // Añade el filtro de autorización
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        // Construye y retorna el SecurityFilterChain
        return http.build();
    }
}
