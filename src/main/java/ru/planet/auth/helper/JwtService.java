package ru.planet.auth.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import ru.tinkoff.kora.common.Component;
import ru.planet.auth.configuration.properties.JwtProperties;
import ru.planet.auth.dto.User;
import ru.planet.auth.repository.AuthRepository;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtService {
    private final JwtProperties jwtProperties;
    private final AuthRepository authRepository;
    private SecretKey secretKey;

    public String generateJwt(User user, List<String> roles) {
        return Jwts.builder()
                .claims(
                        Map.of(
                                ClaimField.USERNAME, user.login(),
                                ClaimField.USER_ID, user.id(),
                                ClaimField.ROLE, roles
                        )
                )
                .expiration(new Date(new Date().getTime() + jwtProperties.jwtExpiration()))
                .subject(user.login())
                .signWith(generateSecretKey())
                .compact();
    }

    public boolean isValidJwt(String token) {
        Claims claims = null;
        try {
             claims = Jwts.parser()
                    .verifyWith(generateSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            return false;
        }

        User user = authRepository.findByLogin(String.valueOf(claims.get(ClaimField.USERNAME)));
        return user != null && claims.getExpiration().after(new Date());
    }

    private SecretKey generateSecretKey() {
        if (secretKey == null) {
            secretKey = Keys.hmacShaKeyFor(jwtProperties.signingKey().getBytes(StandardCharsets.UTF_8));
        }
        return secretKey;
    }

}
