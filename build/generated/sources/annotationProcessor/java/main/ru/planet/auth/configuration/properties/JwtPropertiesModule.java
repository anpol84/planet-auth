package ru.planet.auth.configuration.properties;

import java.util.Optional;
import ru.tinkoff.kora.common.Module;
import ru.tinkoff.kora.common.annotation.Generated;
import ru.tinkoff.kora.config.common.Config;
import ru.tinkoff.kora.config.common.extractor.ConfigValueExtractionException;
import ru.tinkoff.kora.config.common.extractor.ConfigValueExtractor;

@Module
@Generated("ru.tinkoff.kora.config.annotation.processor.processor.ConfigSourceAnnotationProcessor")
public interface JwtPropertiesModule {
  default JwtProperties jwtProperties(Config config,
      ConfigValueExtractor<JwtProperties> extractor) {
    var configValue = config.get("jwt");
    return Optional.ofNullable(extractor.extract(configValue)).orElseThrow(() -> ConfigValueExtractionException.missingValueAfterParse(configValue));
  }
}
