package com.example.springforumapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt.secret}")
    private String secretkey;

    public String generateToken(String username){
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());
        String jwt = JWT.create()
                .withSubject("User details")
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withIssuer("forumapp")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secretkey));
        return jwt;
    }

    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException
    {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secretkey))
                .withSubject("User details")
                .withIssuer("forumapp")
                .build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        String claim =  jwt.getClaim("username").asString();
        return claim;
    }
}
