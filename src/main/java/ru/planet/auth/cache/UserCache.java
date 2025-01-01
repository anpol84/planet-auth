package ru.planet.auth.cache;

import ru.tinkoff.kora.cache.annotation.Cache;
import ru.tinkoff.kora.cache.caffeine.CaffeineCache;
import ru.planet.auth.dto.User;

@Cache("cache.caffeine.userCache")
public interface UserCache extends CaffeineCache<String, User> {
}
