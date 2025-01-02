package ru.planet.auth.operation;

import lombok.RequiredArgsConstructor;
import ru.planet.auth.exception.ValidationException;
import ru.tinkoff.kora.common.Component;
import ru.planet.auth.helper.JwtService;

@Component
@RequiredArgsConstructor
public class CheckTokenOperation {
    private final JwtService jwtService;

    public boolean activate(String jwt) {
        if (jwt.isEmpty()) {
            throw new ValidationException("jwt is not presented or empty");
        }
        return jwtService.isValidJwt(jwt);
    }
}
