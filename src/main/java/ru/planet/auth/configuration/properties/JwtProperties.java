package ru.planet.auth.configuration.properties;

import ru.tinkoff.kora.config.common.annotation.ConfigSource;

@ConfigSource("jwt")
public record JwtProperties(String signingKey, Long jwtExpiration) {
}
