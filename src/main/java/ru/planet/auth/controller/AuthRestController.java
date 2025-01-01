package ru.planet.auth.controller;

import lombok.RequiredArgsConstructor;
import ru.planet.auth.api.AuthApiDelegate;
import ru.planet.auth.api.AuthApiResponses.LoginApiResponse.Login200ApiResponse;
import ru.planet.auth.api.AuthApiResponses.LoginApiResponse;
import ru.planet.auth.model.AuthRequest;
import ru.planet.auth.model.AuthResponse;
import ru.tinkoff.kora.common.Component;
import ru.planet.auth.operation.LoginOperation;
import ru.tinkoff.kora.logging.common.annotation.Log;


@Component
@RequiredArgsConstructor
public class AuthRestController implements AuthApiDelegate {
    private final LoginOperation loginOperation;

    @Override
    @Log
    public LoginApiResponse login(AuthRequest authRequest) throws Exception {
        return new Login200ApiResponse(
                new AuthResponse(
                        loginOperation.activate(authRequest.login(), authRequest.password())));
    }

}

