package ru.planet.auth.configuration.properties;

import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Map;
import ru.tinkoff.kora.common.annotation.Generated;
import ru.tinkoff.kora.config.common.ConfigValue;
import ru.tinkoff.kora.config.common.PathElement;
import ru.tinkoff.kora.config.common.extractor.ConfigValueExtractionException;
import ru.tinkoff.kora.config.common.extractor.ConfigValueExtractor;

@Generated("ru.tinkoff.kora.config.annotation.processor.ConfigParserGenerator")
public final class $JwtProperties_ConfigValueExtractor implements ConfigValueExtractor<JwtProperties> {
  private static final PathElement.Key _signingKey_path = PathElement.get("signingKey");

  private static final PathElement.Key _jwtExpiration_path = PathElement.get("jwtExpiration");

  public $JwtProperties_ConfigValueExtractor() {
  }

  @Override
  public JwtProperties extract(ConfigValue<?> _sourceValue) {
    if (_sourceValue instanceof ConfigValue.NullValue _nullValue) {
      _sourceValue = new ConfigValue.ObjectValue(_sourceValue.origin(), Map.of());
    }
    var _config = _sourceValue.asObject();
    var signingKey = this.parse_signingKey(_config);
    var jwtExpiration = this.parse_jwtExpiration(_config);
    return new JwtProperties(
      signingKey,
      jwtExpiration
    );
  }

  private String parse_signingKey(ConfigValue.ObjectValue config) {
    var value = config.get(_signingKey_path);
    if (value instanceof ConfigValue.NullValue nullValue) {
      throw ConfigValueExtractionException.missingValue(nullValue);
    }
    return value.asString();
  }

  private Long parse_jwtExpiration(ConfigValue.ObjectValue config) {
    var value = config.get(_jwtExpiration_path);
    if (value instanceof ConfigValue.NullValue nullValue) {
      throw ConfigValueExtractionException.missingValue(nullValue);
    }
    return value.asNumber().longValue();
  }
}
