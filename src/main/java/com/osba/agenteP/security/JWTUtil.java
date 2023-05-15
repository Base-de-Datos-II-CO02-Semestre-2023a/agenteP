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

    public String generateToken(String rfc) throws IllegalArgumentException{
        Date date = new Date();

        return JWT.create()
                .withSubject("User Details")
                .withClaim("rfc", rfc)
                .withIssuedAt(new Date())
                .withIssuer("osba")
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveSubject(String token) throws IllegalArgumentException{
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("osba")
                .build();


    try {
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        System.out.println("Decodificado");
        return decodedJWT.getClaim("rfc").asString();
    }catch (Exception e){
        System.out.println(e.getMessage());
    }
    return null;
    }

}