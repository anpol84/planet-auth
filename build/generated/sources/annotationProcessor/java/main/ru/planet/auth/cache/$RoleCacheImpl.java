package ru.planet.auth.cache;

import java.lang.Long;
import java.lang.String;
import java.util.List;
import ru.tinkoff.kora.cache.caffeine.AbstractCaffeineCache;
import ru.tinkoff.kora.cache.caffeine.CaffeineCacheConfig;
import ru.tinkoff.kora.cache.caffeine.CaffeineCacheFactory;
import ru.tinkoff.kora.cache.caffeine.CaffeineCacheTelemetry;
import ru.tinkoff.kora.common.annotation.Generated;

@Generated("ru.tinkoff.kora.cache.annotation.processor.CacheAnnotationProcessor")
final class $RoleCacheImpl extends AbstractCaffeineCache<Long, List<String>> implements RoleCache {
  $RoleCacheImpl(CaffeineCacheConfig config, CaffeineCacheFactory factory,
      CaffeineCacheTelemetry telemetry) {
    super("cache.caffeine.roleCache", config, factory, telemetry);
  }
}
