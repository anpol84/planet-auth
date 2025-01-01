package ru.planet.auth.configuration.properties;

import java.util.Optional;
import ru.tinkoff.kora.common.Module;
import ru.tinkoff.kora.common.annotation.Generated;
import ru.tinkoff.kora.config.common.Config;
import ru.tinkoff.kora.config.common.extractor.ConfigValueExtractionException;
import ru.tinkoff.kora.config.common.extractor.ConfigValueExtractor;

@Module
@Generated("ru.tinkoff.kora.config.annotation.processor.processor.ConfigSourceAnnotationProcessor")
public interface BcryptPropertiesModule {
  default BcryptProperties bcryptProperties(Config config,
      ConfigValueExtractor<BcryptProperties> extractor) {
    var configValue = config.get("bcrypt");
    return Optional.ofNullable(extractor.extract(configValue)).orElseThrow(() -> ConfigValueExtractionException.missingValueAfterParse(configValue));
  }
}
