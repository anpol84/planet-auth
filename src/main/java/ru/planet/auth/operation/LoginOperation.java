package ru.planet.auth.operation;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import ru.planet.auth.exception.BusinessException;
import ru.planet.auth.exception.ValidationException;
import ru.tinkoff.kora.cache.LoadableCache;
import ru.tinkoff.kora.common.Component;
import ru.planet.auth.configuration.properties.BcryptProperties;
import ru.planet.auth.dto.User;
import ru.planet.auth.helper.JwtService;

import java.util.List;;

@Component
@RequiredArgsConstructor
public class LoginOperation {
    private final JwtService jwtService;
    private final BcryptProperties bcryptProperties;
    private final LoadableCache<String, User> userCache;
    private final LoadableCache<Long, List<String>> roleCache;

    public String activate(String login, String password) {
        if (login.isEmpty() || password.isEmpty()) {
            throw new ValidationException("login or password is not presented or empty");
        }
        var user = userCache.get(login);
        String hashPassword = BCrypt.hashpw(password, bcryptProperties.salt());
        if (user != null && user.password().equals(hashPassword)) {
            return jwtService.generateJwt(user, roleCache.get(user.id()));
        }
        throw new BusinessException("login or password incorrect");
    }
}
