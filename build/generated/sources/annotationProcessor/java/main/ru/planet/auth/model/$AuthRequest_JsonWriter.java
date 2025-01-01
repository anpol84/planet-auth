package ru.planet.auth.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.SerializedString;
import jakarta.annotation.Nullable;
import java.io.IOException;
import java.lang.Override;
import ru.tinkoff.kora.common.annotation.Generated;
import ru.tinkoff.kora.json.common.JsonWriter;

@Generated("ru.tinkoff.kora.json.annotation.processor.writer.JsonWriterGenerator")
public final class $AuthRequest_JsonWriter implements JsonWriter<AuthRequest> {
  private static final SerializedString _login_optimized_field_name = new SerializedString("login");

  private static final SerializedString _password_optimized_field_name = new SerializedString("password");

  public $AuthRequest_JsonWriter() {
  }

  @Override
  public final void write(JsonGenerator _gen, @Nullable AuthRequest _object) throws IOException {
    if (_object == null) {
      _gen.writeNull();
      return;
    }
    _gen.writeStartObject(_object);
    if (_object.login() != null) {
      _gen.writeFieldName(_login_optimized_field_name);
      _gen.writeString(_object.login());
    }
    if (_object.password() != null) {
      _gen.writeFieldName(_password_optimized_field_name);
      _gen.writeString(_object.password());
    }
    _gen.writeEndObject();
  }
}
