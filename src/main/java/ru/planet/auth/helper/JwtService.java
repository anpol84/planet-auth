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
        Claims claims = getClaims(token);
        if (claims == null) {
            return false;
        }
        User user = authRepository.findByLogin(String.valueOf(claims.get(ClaimField.USERNAME)));
        return user != null && claims.getExpiration().after(new Date());
    }

    public boolean isValidJwtWithId(String token, Long id) {
        Claims claims = getClaims(token);
        if (claims == null) {
            return false;
        }
        return isValidAdmin(token) ||
                Long.valueOf(String.valueOf(claims.get(ClaimField.USER_ID))).equals(id);
    }

    public boolean isValidAdmin(String token) {
        Claims claims = getClaims(token);
        if (claims == null) {
            return false;
        }
        User user = authRepository.findByLogin(String.valueOf(claims.get(ClaimField.USERNAME)));
        return user != null &&
                claims.getExpiration().after(new Date()) &&
                (String.valueOf(claims.get(ClaimField.ROLE)).contains("ROLE_ADMIN"));
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(generateSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            return null;
        }
    }

    private SecretKey generateSecretKey() {
        if (secretKey == null) {
            secretKey = Keys.hmacShaKeyFor(jwtProperties.signingKey().getBytes(StandardCharsets.UTF_8));
        }
        return secretKey;
    }

}
