package com.kk.strong.security;

import com.kk.strong.model.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {

    protected static final int ACCESS_TOKEN_EXPIRY_SECONDS = 60 * 60 * 24;
    protected static final String TOKEN_SECRET = "token_secret";

    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        UsernamePasswordTokenAuthenticationFilter usernamePasswordTokenAuthenticationFilter = new UsernamePasswordTokenAuthenticationFilter(authenticationManager(authenticationConfiguration));
        usernamePasswordTokenAuthenticationFilter.setFilterProcessesUrl("/login");

        httpSecurity.csrf().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.authorizeRequests().anyRequest().authenticated();

        httpSecurity.addFilter(usernamePasswordTokenAuthenticationFilter);
        httpSecurity.addFilterBefore(new TokenAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
