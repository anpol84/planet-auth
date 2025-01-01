package ru.planet.auth;

import ru.planet.auth.api.AuthApiControllerModule;
import ru.planet.auth.cache.$RoleCacheModule;
import ru.planet.auth.cache.$UserCacheModule;
import ru.planet.auth.configuration.AppCacheModule;
import ru.planet.auth.configuration.properties.BcryptPropertiesModule;
import ru.planet.auth.configuration.properties.JwtPropertiesModule;
import ru.planet.auth.controller.OpenApiControllerModule;
import ru.tinkoff.kora.common.annotation.Generated;

@Generated("ru.tinkoff.kora.kora.app.annotation.processor.KoraAppProcessor")
public final class $ApplicationImpl implements Application {
  public final AuthApiControllerModule module0 = new AuthApiControllerModule(){};

  public final $RoleCacheModule module1 = new $RoleCacheModule(){};

  public final $UserCacheModule module2 = new $UserCacheModule(){};

  public final AppCacheModule module3 = new AppCacheModule(){};

  public final BcryptPropertiesModule module4 = new BcryptPropertiesModule(){};

  public final JwtPropertiesModule module5 = new JwtPropertiesModule(){};

  public final OpenApiControllerModule module6 = new OpenApiControllerModule(){};
}
