package com.example.Aluguel.de.imoveis.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private SecurityFilter securityFilter;
    @Autowired
    private Environment env;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // H2
        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }
        return http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Define a política de sessão como stateless
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                    corsConfig.setAllowedOrigins(List.of("http://127.0.0.1:5500")); // Permita seu front-end
                    corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos permitidos
                    corsConfig.setAllowCredentials(true); // Permite credenciais (cookies)
                    corsConfig.setAllowedHeaders(List.of("*")); // Permite todos os cabeçalhos
                    return corsConfig;
                }))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/insert").permitAll()
                        .requestMatchers(HttpMethod.GET, "/login").permitAll() // Permitir acesso à página de login
                        .requestMatchers(HttpMethod.GET, "/user").permitAll() // Ajuste para o endpoint correto
                        .requestMatchers(HttpMethod.GET, "/owner/addImovel").permitAll() // Ajuste para o endpoint correto
                        .requestMatchers(HttpMethod.GET, "/frontInsert.html").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()// Ajuste para o endpoint correto
                        .anyRequest().authenticated()) // Qualquer outra requisição precisa de autenticação
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona seu filtro de segurança
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
