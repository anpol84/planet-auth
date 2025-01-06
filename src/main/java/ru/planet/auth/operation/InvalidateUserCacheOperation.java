package ru.planet.auth.operation;

import lombok.RequiredArgsConstructor;
import ru.planet.auth.cache.UserCache;
import ru.tinkoff.kora.cache.annotation.CacheInvalidate;
import ru.tinkoff.kora.common.Component;
import ru.tinkoff.kora.generated.grpc.PlanetAuth.InvalidateUserCacheResponse;

@Component
@RequiredArgsConstructor
public class InvalidateUserCacheOperation {

    @CacheInvalidate(UserCache.class)
    public InvalidateUserCacheResponse activate(String login){
        return InvalidateUserCacheResponse.newBuilder().build();
    }
}
