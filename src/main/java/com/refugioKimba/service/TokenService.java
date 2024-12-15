package com.refugioKimba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Autowired
    private JwtEncoder jwtEncoder;

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("roles", roles)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
/*
@Service
public class TokenService {

    @Autowired
    private RsaKeyProperties rsaKeyProperties;

    private static final long EXPIRATION_TIME = 86400000L; // 24 horas

    // Método para generar el token JWT
    public String generateToken(Usuario usuario) {
        try {
            // Construcción del token JWT
            String token = Jwts.builder()
                    .setSubject(usuario.getEmail())
                    .claim("rol", usuario.getRol().name())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                    .signWith(SignatureAlgorithm.HS256, "secret")
                    .compact();

            return token;
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el token", e);
        }
    }



    // Método para validar el token JWT
    public Boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(getPublicKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false; // El token es inválido
        }
    }

    // Obtener la clave privada para firmar el token
    private PrivateKey getPrivateKey() {
        return rsaKeyProperties.getPrivateKey();    }

    // Obtener la clave pública para validar el token
    private PublicKey getPublicKey() {
        return rsaKeyProperties.getPublicKey();    }
}
*/