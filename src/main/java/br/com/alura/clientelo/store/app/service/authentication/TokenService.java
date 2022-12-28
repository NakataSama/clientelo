package br.com.alura.clientelo.store.app.service.authentication;

import br.com.alura.clientelo.store.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${clientelo.security.jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("clientelo")
                    .withSubject(user.getEmail())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error while generating JWT Token", exception);
        }
    }

    public String getSubject(String JWTToken) {
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWTToken);
            return JWT.require(algorithm)
                    .withIssuer("clientelo")
                    .build()
                    .verify(JWTToken)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("JWT Token is invalid or is expired");
        }
    }
}
