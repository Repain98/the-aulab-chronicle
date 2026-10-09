package it.aulab.theaulabchronicle.config;

import it.aulab.theaulabchronicle.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
// NOTA: con Spring Security 7 (Spring Boot 4.1.1) AntPathRequestMatcher è stato rimosso, PathPatternRequestMatcher è il suo equivalente
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((authorize) -> authorize.requestMatchers("/register/**").permitAll().requestMatchers("/admin/dashboard", "/categories/create", "/categories/edit/{id}", "/categories/update/{id}", "/categories/update/{id}").hasRole("ADMIN").requestMatchers("/revisor/dashboard", "/revisor/detail/{id}", "/accept").hasRole("REVISOR").requestMatchers("/register", "/", "/articles", "/images/**", "/articles/detail/**", "/categories/search/{id}", "/search/{id}").permitAll().anyRequest().authenticated()).formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/").permitAll()).logout(logout -> logout.logoutRequestMatcher(PathPatternRequestMatcher.withDefaults().matcher("/logout")).permitAll()).exceptionHandling(exception -> exception.accessDeniedPage("/error/403")).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).maximumSessions(1).expiredUrl("/login?session-expired=true"));
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}