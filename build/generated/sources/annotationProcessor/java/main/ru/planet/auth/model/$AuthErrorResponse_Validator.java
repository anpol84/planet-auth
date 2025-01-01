package ru.planet.auth.model;

import java.lang.Override;
import java.util.ArrayList;
import java.util.List;
import ru.tinkoff.kora.common.annotation.Generated;
import ru.tinkoff.kora.validation.common.ValidationContext;
import ru.tinkoff.kora.validation.common.Validator;
import ru.tinkoff.kora.validation.common.Violation;

@Generated("ru.tinkoff.kora.validation.annotation.processor.ValidatorGenerator")
public final class $AuthErrorResponse_Validator implements Validator<AuthErrorResponse> {
  public $AuthErrorResponse_Validator() {
  }

  @Override
  public List<Violation> validate(AuthErrorResponse value, ValidationContext context) {
    if (value == null) {
        return List.of(context.violates("AuthErrorResponse input must be non null, but was null"));
    }

    final List<Violation> _violations = new ArrayList<>();



    return _violations;
  }
}
