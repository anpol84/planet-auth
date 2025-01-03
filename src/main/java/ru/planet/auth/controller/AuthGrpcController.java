package ru.planet.auth.controller;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import ru.planet.auth.operation.CheckTokenWithIdOperation;
import ru.tinkoff.kora.common.Component;
import ru.planet.auth.operation.CheckTokenOperation;
import ru.planet.auth.operation.LoginOperation;
import ru.tinkoff.kora.generated.grpc.AuthServiceGrpc.AuthServiceImplBase;
import ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdRequest;
import ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdResponse;
import ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenRequest;
import ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenResponse;
import ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginRequest;
import ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginResponse;
import ru.tinkoff.kora.logging.common.annotation.Log;

import static ru.planet.auth.helper.GrpcExecutionHelper.executeSingle;

@Component
@RequiredArgsConstructor
public class AuthGrpcController extends AuthServiceImplBase {
    private final LoginOperation loginOperation;
    private final CheckTokenOperation checkTokenOperation;
    private final CheckTokenWithIdOperation checkTokenWithIdOperation;

    @Override
    @Log
    public void checkToken(CheckTokenRequest request, StreamObserver<CheckTokenResponse> responseObserver) {
        executeSingle(responseObserver,
                () -> CheckTokenResponse.newBuilder()
                        .setIsValid(checkTokenOperation.activate(request.getToken()))
                        .build());
    }

    @Override
    @Log
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        executeSingle(responseObserver,
                () -> LoginResponse.newBuilder()
                        .setToken(loginOperation.activate(request.getLogin(), request.getPassword()))
                        .build());
    }

    @Override
    @Log
    public void checkTokenWithId(CheckTokenWithIdRequest request, StreamObserver<CheckTokenWithIdResponse> responseObserver) {
        executeSingle(responseObserver, () -> checkTokenWithIdOperation.activate(request.getToken(), request.getId()));
    }
}
