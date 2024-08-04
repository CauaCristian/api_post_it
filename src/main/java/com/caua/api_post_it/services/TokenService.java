package com.caua.api_post_it.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.caua.api_post_it.models.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(UserModel user) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("postIT-api").withSubject(user.getUsername()).withExpiresAt(generateExpirationDate()).sign(algorithm);
            return token;
        }catch (JWTCreationException exception ){
            throw new RuntimeException("JWT generation failed", exception);
        }
    }
    public String verifyToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("postIT-api").build().verify(token).getSubject();
        }catch (JWTVerificationException exception){
            throw new RuntimeException("JWT verification failed", exception);
        }
    }
    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));
    }
}
