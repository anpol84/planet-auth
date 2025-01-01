package ru.planet.auth.exception;

import io.micrometer.core.instrument.config.validate.ValidationException;
import lombok.extern.slf4j.Slf4j;
import ru.planet.auth.model.AuthErrorResponse;
import ru.planet.auth.model.AuthRequest;
import ru.planet.auth.model.AuthResponse;
import ru.tinkoff.kora.common.Component;
import ru.tinkoff.kora.common.Context;
import ru.tinkoff.kora.common.Tag;
import ru.tinkoff.kora.http.common.body.HttpBody;
import ru.tinkoff.kora.http.server.common.*;
import ru.tinkoff.kora.json.common.JsonWriter;

import java.util.concurrent.CompletionStage;

@Tag(HttpServerModule.class)
@Component
@Slf4j
public final class HttpExceptionHandler implements HttpServerInterceptor {

    private final JsonWriter<AuthErrorResponse> errorJsonWriter;

    public HttpExceptionHandler(JsonWriter<AuthErrorResponse> errorJsonWriter) {
        this.errorJsonWriter = errorJsonWriter;
    }

    @Override
    public CompletionStage<HttpServerResponse> intercept(Context context, HttpServerRequest request, InterceptChain chain)
            throws Exception {
        return chain.process(context, request).exceptionally(e -> {
            if (e instanceof HttpServerResponseException ex) {
                return ex;
            }

            var body = HttpBody.json(errorJsonWriter.toByteArrayUnchecked(new AuthErrorResponse(e.getMessage())));
            if (e instanceof BusinessException) {
                log.warn("Request '{} {}' failed due to {}", request.method(), request.path(), e.getMessage());
                return HttpServerResponse.of(422, body);
            } else {
                log.error("Request '{} {}' failed", request.method(), request.path(), e);
                return HttpServerResponse.of(500, body);
            }
        });
    }
}
