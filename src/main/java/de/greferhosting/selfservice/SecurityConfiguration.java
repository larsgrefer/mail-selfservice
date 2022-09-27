package de.greferhosting.selfservice;

import de.greferhosting.selfservice.service.Sha256CryptPasswordEncoder;
import de.greferhosting.selfservice.service.Sha512CryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> idToPasswordEncoder = new HashMap<>();

        idToPasswordEncoder.put("SHA512-CRYPT", new Sha512CryptPasswordEncoder());
        idToPasswordEncoder.put("SHA256-CRYPT", new Sha256CryptPasswordEncoder());

        return new DelegatingPasswordEncoder("SHA512-CRYPT", idToPasswordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin().and()
                .httpBasic().and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        return http.build();
    }
}
