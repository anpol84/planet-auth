package ru.planet.auth.cache;

import ru.tinkoff.kora.cache.caffeine.CaffeineCacheConfig;
import ru.tinkoff.kora.cache.caffeine.CaffeineCacheFactory;
import ru.tinkoff.kora.cache.caffeine.CaffeineCacheTelemetry;
import ru.tinkoff.kora.common.Module;
import ru.tinkoff.kora.common.Tag;
import ru.tinkoff.kora.common.annotation.Generated;
import ru.tinkoff.kora.config.common.Config;
import ru.tinkoff.kora.config.common.extractor.ConfigValueExtractor;

@Generated("ru.tinkoff.kora.cache.annotation.processor.CacheAnnotationProcessor")
@Module
public interface $RoleCacheModule {
  default RoleCache $RoleCacheImpl_Impl(@Tag(RoleCache.class) CaffeineCacheConfig config,
      CaffeineCacheFactory factory, CaffeineCacheTelemetry telemetry) {
    return new $RoleCacheImpl(config, factory, telemetry);
  }

  @Tag(RoleCache.class)
  default CaffeineCacheConfig RoleCache_Config(Config config,
      ConfigValueExtractor<CaffeineCacheConfig> extractor) {
    return extractor.extract(config.get("cache.caffeine.roleCache"));
  }
}
