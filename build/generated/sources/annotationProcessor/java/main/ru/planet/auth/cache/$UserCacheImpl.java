package ru.planet.auth.cache;

import java.lang.String;
import ru.planet.auth.dto.User;
import ru.tinkoff.kora.cache.caffeine.AbstractCaffeineCache;
import ru.tinkoff.kora.cache.caffeine.CaffeineCacheConfig;
import ru.tinkoff.kora.cache.caffeine.CaffeineCacheFactory;
import ru.tinkoff.kora.cache.caffeine.CaffeineCacheTelemetry;
import ru.tinkoff.kora.common.annotation.Generated;

@Generated("ru.tinkoff.kora.cache.annotation.processor.CacheAnnotationProcessor")
final class $UserCacheImpl extends AbstractCaffeineCache<String, User> implements UserCache {
  $UserCacheImpl(CaffeineCacheConfig config, CaffeineCacheFactory factory,
      CaffeineCacheTelemetry telemetry) {
    super("cache.caffeine.userCache", config, factory, telemetry);
  }
}
