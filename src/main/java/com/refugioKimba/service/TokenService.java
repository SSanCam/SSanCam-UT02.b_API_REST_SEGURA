package com.refugioKimba.service;

import com.refugioKimba.model.Usuario;
import com.refugioKimba.security.RsaKeyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

@Service
public class TokenService {

    @Autowired
    private RsaKeyProperties rsaKeyProperties;

    private static final long EXPIRATION_TIME = 86400000L; // 24 horas

    // Método para generar el token JWT
    public String generateToken(Usuario usuario) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .claim("role", usuario.getRol()) // Agregar el rol al token
                .signWith(getPrivateKey())
                .compact();
    }

    // Método para validar el token JWT
    public Boolean validateToken(String token) {
        try {
            Jwts.builder()
                    .se(getPublicKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false; // El token es inválido
        }
    }

    // Obtener la clave privada para firmar el token
    private PrivateKey getPrivateKey() {
        return rsaKeyProperties.privateKey();
    }

    // Obtener la clave pública para validar el token
    private PublicKey getPublicKey() {
        return rsaKeyProperties.publicKey();
    }
}
