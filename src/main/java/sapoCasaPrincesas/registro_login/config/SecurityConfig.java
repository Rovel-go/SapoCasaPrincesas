package sapoCasaPrincesas.registro_login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Rutas públicas (POST)
                        .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/registro").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/recuperar").permitAll()

                        // Rutas públicas (GET)
                        .requestMatchers(HttpMethod.GET, "/salones/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/colaboradores/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/servicios/**").permitAll()

                        // Rutas admin
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // Todo lo demás
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


