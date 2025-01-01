package ru.planet.auth.configuration;

import ru.tinkoff.kora.cache.LoadableCache;
import ru.tinkoff.kora.common.Module;
import ru.planet.auth.cache.RoleCache;
import ru.planet.auth.cache.UserCache;
import ru.planet.auth.dto.User;
import ru.planet.auth.repository.AuthRepository;

import java.util.List;

@Module
public interface AppCacheModule {
    default LoadableCache<String, User> userCache(UserCache userCache, AuthRepository authRepository) {
        return userCache.asLoadableSimple(authRepository::findByLogin);
    }

    default LoadableCache<Long, List<String>> roleCache(RoleCache roleCache, AuthRepository authRepository) {
        return roleCache.asLoadableSimple(authRepository::findRoles);
    }
}
