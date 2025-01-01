package ru.planet.auth.model;

import java.lang.Override;
import java.util.ArrayList;
import java.util.List;
import ru.tinkoff.kora.common.annotation.Generated;
import ru.tinkoff.kora.validation.common.ValidationContext;
import ru.tinkoff.kora.validation.common.Validator;
import ru.tinkoff.kora.validation.common.Violation;

@Generated("ru.tinkoff.kora.validation.annotation.processor.ValidatorGenerator")
public final class $Login400Response_Validator implements Validator<Login400Response> {
  public $Login400Response_Validator() {
  }

  @Override
  public List<Violation> validate(Login400Response value, ValidationContext context) {
    if (value == null) {
        return List.of(context.violates("Login400Response input must be non null, but was null"));
    }

    final List<Violation> _violations = new ArrayList<>();



    return _violations;
  }
}
