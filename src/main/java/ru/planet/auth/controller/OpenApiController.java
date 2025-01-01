package ru.planet.auth.controller;

import lombok.RequiredArgsConstructor;
import ru.planet.auth.helper.OpenApiProvider;
import ru.tinkoff.kora.http.common.HttpMethod;
import ru.tinkoff.kora.http.common.annotation.HttpRoute;
import ru.tinkoff.kora.http.common.body.HttpBody;
import ru.tinkoff.kora.http.server.common.HttpServerResponse;
import ru.tinkoff.kora.http.server.common.annotation.HttpController;

import static java.nio.charset.StandardCharsets.UTF_8;

@RequiredArgsConstructor
@HttpController
public final class OpenApiController {
    private final OpenApiProvider openApiProvider;

    @HttpRoute(method = HttpMethod.GET, path = "/docs/openapi.yaml")
    public HttpServerResponse getOpenApiContract() {
        if (openApiProvider.isEnabled()) {
            return HttpServerResponse.of(200, HttpBody.plaintext(UTF_8.encode(openApiProvider.getContract())));
        } else {
            return HttpServerResponse.of(403);
        }
    }

    @HttpRoute(method = HttpMethod.OPTIONS, path = "/*")
    public HttpServerResponse preflight() {
        return HttpServerResponse.of(200);
    }


}
