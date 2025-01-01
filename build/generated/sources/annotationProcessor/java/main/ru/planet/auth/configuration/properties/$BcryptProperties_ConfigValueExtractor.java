package ru.planet.auth.configuration.properties;

import java.lang.Override;
import java.lang.String;
import java.util.Map;
import ru.tinkoff.kora.common.annotation.Generated;
import ru.tinkoff.kora.config.common.ConfigValue;
import ru.tinkoff.kora.config.common.PathElement;
import ru.tinkoff.kora.config.common.extractor.ConfigValueExtractionException;
import ru.tinkoff.kora.config.common.extractor.ConfigValueExtractor;

@Generated("ru.tinkoff.kora.config.annotation.processor.ConfigParserGenerator")
public final class $BcryptProperties_ConfigValueExtractor implements ConfigValueExtractor<BcryptProperties> {
  private static final PathElement.Key _salt_path = PathElement.get("salt");

  public $BcryptProperties_ConfigValueExtractor() {
  }

  @Override
  public BcryptProperties extract(ConfigValue<?> _sourceValue) {
    if (_sourceValue instanceof ConfigValue.NullValue _nullValue) {
      _sourceValue = new ConfigValue.ObjectValue(_sourceValue.origin(), Map.of());
    }
    var _config = _sourceValue.asObject();
    var salt = this.parse_salt(_config);
    return new BcryptProperties(
      salt
    );
  }

  private String parse_salt(ConfigValue.ObjectValue config) {
    var value = config.get(_salt_path);
    if (value instanceof ConfigValue.NullValue nullValue) {
      throw ConfigValueExtractionException.missingValue(nullValue);
    }
    return value.asString();
  }
}
