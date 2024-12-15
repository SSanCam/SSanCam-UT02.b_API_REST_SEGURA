package com.refugioKimba.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private RsaKeyProperties rsaKeys;

    @Value("classpath:certs/public.pem")
    private Resource publicKeyResource;

    @Value("classpath:certs/private.pem")
    private Resource privateKeyResource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Rutas públicas
                        .requestMatchers(HttpMethod.POST, "/usuarios/login", "/usuarios/register").permitAll()

                        // Rutas de Usuarios
                        .requestMatchers(HttpMethod.GET, "/usuarios/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/usuarios/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/usuarios/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/{id}").hasRole("ADMIN")

                        // Rutas de Animales
                        .requestMatchers(HttpMethod.POST, "/animales/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/animales/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/animales").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/animales/{id}").hasRole("ADMIN")

                        // Rutas de Adopciones
                        .requestMatchers(HttpMethod.POST, "/adopciones/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/adopciones/").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder()))) // Correcta configuración de JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
}
