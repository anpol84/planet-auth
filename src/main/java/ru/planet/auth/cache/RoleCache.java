package ru.planet.auth.cache;

import ru.tinkoff.kora.cache.annotation.Cache;
import ru.tinkoff.kora.cache.caffeine.CaffeineCache;

import java.util.List;

@Cache("cache.caffeine.roleCache")
public interface RoleCache extends CaffeineCache<Long, List<String>> {
}
