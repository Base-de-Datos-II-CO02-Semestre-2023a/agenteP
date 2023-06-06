package com.osba.agenteP.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Esta clase se utiliza para generar los tokens JWT
 */
@Component
public class JWTUtil {
    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(Integer id) throws IllegalArgumentException{
        return JWT.create()
                .withSubject("User Details")
                .withClaim("id", id)
                .withClaim("timestamp", new Date().getTime())
                .withIssuedAt(new Date())
                .withIssuer("osba")
                .sign(Algorithm.HMAC256(secret));
    }

    public Integer validateTokenAndRetrieveSubject(String token) throws IllegalArgumentException{
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("osba")
                .build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getClaim("id").asInt();
    }

}
