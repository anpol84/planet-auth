package ru.planet.auth.api;

import java.lang.RuntimeException;
import java.util.concurrent.CompletionException;
import ru.planet.auth.model.AuthRequest;
import ru.planet.auth.model.ValidateRequest;
import ru.tinkoff.kora.common.Module;
import ru.tinkoff.kora.common.Tag;
import ru.tinkoff.kora.common.annotation.Generated;
import ru.tinkoff.kora.http.server.common.HttpServerResponse;
import ru.tinkoff.kora.http.server.common.HttpServerResponseException;
import ru.tinkoff.kora.http.server.common.handler.BlockingRequestExecutor;
import ru.tinkoff.kora.http.server.common.handler.HttpServerRequestHandler;
import ru.tinkoff.kora.http.server.common.handler.HttpServerRequestHandlerImpl;
import ru.tinkoff.kora.http.server.common.handler.HttpServerRequestMapper;

@Generated("ru.tinkoff.kora.http.server.annotation.processor.HttpControllerProcessor")
@Module
public interface AuthApiControllerModule {
  default HttpServerRequestHandler post_api_validate(AuthApiController _controller,
      @Tag({ru.tinkoff.kora.json.common.annotation.Json.class}) HttpServerRequestMapper<ValidateRequest> validateRequestHttpRequestMapper,
      AuthApiServerResponseMappers.CheckTokenApiResponseMapper _responseMapper,
      BlockingRequestExecutor _executor) {
    return HttpServerRequestHandlerImpl.of("POST", "/api/validate", (_ctx, _request) -> {

      return _executor.execute(_ctx, () -> {
        final ValidateRequest validateRequest;
        try {
          validateRequest = validateRequestHttpRequestMapper.apply(_request);
        } catch (CompletionException _e) {
          if (_e.getCause() instanceof HttpServerResponse && _e.getCause() instanceof RuntimeException) throw (RuntimeException) _e.getCause();
          throw HttpServerResponseException.of(400, _e.getCause());
        } catch (Exception _e) {
          if (_e instanceof HttpServerResponse) throw _e;
          throw HttpServerResponseException.of(400, _e);
        }
        var _result = _controller.checkToken(validateRequest);
        return _responseMapper.apply(_ctx, _request, _result);
      });
    });
  }

  default HttpServerRequestHandler post_api_auth(AuthApiController _controller,
      @Tag({ru.tinkoff.kora.json.common.annotation.Json.class}) HttpServerRequestMapper<AuthRequest> authRequestHttpRequestMapper,
      AuthApiServerResponseMappers.LoginApiResponseMapper _responseMapper,
      BlockingRequestExecutor _executor) {
    return HttpServerRequestHandlerImpl.of("POST", "/api/auth", (_ctx, _request) -> {

      return _executor.execute(_ctx, () -> {
        final AuthRequest authRequest;
        try {
          authRequest = authRequestHttpRequestMapper.apply(_request);
        } catch (CompletionException _e) {
          if (_e.getCause() instanceof HttpServerResponse && _e.getCause() instanceof RuntimeException) throw (RuntimeException) _e.getCause();
          throw HttpServerResponseException.of(400, _e.getCause());
        } catch (Exception _e) {
          if (_e instanceof HttpServerResponse) throw _e;
          throw HttpServerResponseException.of(400, _e);
        }
        var _result = _controller.login(authRequest);
        return _responseMapper.apply(_ctx, _request, _result);
      });
    });
  }
}
