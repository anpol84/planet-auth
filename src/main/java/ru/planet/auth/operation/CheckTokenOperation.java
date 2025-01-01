package ru.planet.auth.operation;

import lombok.RequiredArgsConstructor;
import ru.tinkoff.kora.common.Component;
import ru.planet.auth.helper.JwtService;
import ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenResponse;

@Component
@RequiredArgsConstructor
public class CheckTokenOperation {
    private final JwtService jwtService;

    public CheckTokenResponse activate(String jwt) {
        return CheckTokenResponse.newBuilder()
                .setIsValid(jwtService.isValidJwt(jwt))
                .build();
    }
}
