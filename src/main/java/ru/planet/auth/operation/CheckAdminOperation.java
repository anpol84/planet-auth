package ru.planet.auth.operation;

import lombok.RequiredArgsConstructor;
import ru.planet.auth.helper.JwtService;
import ru.tinkoff.kora.common.Component;
import ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckAdminRequest;
import ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckAdminResponse;

@Component
@RequiredArgsConstructor
public class CheckAdminOperation {
    private final JwtService jwtService;

    public CheckAdminResponse activate(CheckAdminRequest request) {
        return CheckAdminResponse.newBuilder()
                .setIsValid(jwtService.isValidAdmin(request.getToken()))
                .build();
    }
}
