package com.spring.seccurity.Security.helpers.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <p>
 *     Para desabilitar as configuraçãoes padrões do SpringSecurity Essa classe é implementada com as anotações {@link Configuration}
 *     para indicar ao Spring que se trata de uma classe de configuração, e a anotação {@link EnableWebSecurity}, para
 *     utilizar as configurações de corrente de segurança dessa classe.
 * </p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize // Define quem pode utilizar os endpoints da aplicação de acordo com as Roles
                        .requestMatchers(HttpMethod.POST, "/products/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/products/admin").hasRole("ADMIN") // Somente Administradores do sistema tem acesso a esses endponts
                        .requestMatchers(HttpMethod.DELETE, "/products/admin").hasRole("ADMIN")
                        .anyRequest().permitAll() // Define que para todos os outros endpoints não é necessario altenticação.
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona nossa checagem de tokens antes das feitas acima
                .build();
    }

    /**
     * Função responsavel por gerar o AuthenticationManager com as especificações desta classe.
     *
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Função responsavel por gerar o BCrypt da aplicação.
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
