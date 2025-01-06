package ru.planet.auth.operation;

import lombok.RequiredArgsConstructor;
import ru.planet.auth.helper.JwtService;
import ru.tinkoff.kora.common.Component;
import ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdResponse;

@Component
@RequiredArgsConstructor
public class CheckTokenWithIdOperation {
    private final JwtService jwtService;

    public CheckTokenWithIdResponse activate(String token, long id) {
        return CheckTokenWithIdResponse.newBuilder()
                .setIsValid(jwtService.isValidJwtWithId(token, id))
                .build();
    }
}
