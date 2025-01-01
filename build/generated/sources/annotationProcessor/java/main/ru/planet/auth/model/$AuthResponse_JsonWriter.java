package ru.planet.auth.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.SerializedString;
import jakarta.annotation.Nullable;
import java.io.IOException;
import java.lang.Override;
import ru.tinkoff.kora.common.annotation.Generated;
import ru.tinkoff.kora.json.common.JsonWriter;

@Generated("ru.tinkoff.kora.json.annotation.processor.writer.JsonWriterGenerator")
public final class $AuthResponse_JsonWriter implements JsonWriter<AuthResponse> {
  private static final SerializedString _token_optimized_field_name = new SerializedString("token");

  public $AuthResponse_JsonWriter() {
  }

  @Override
  public final void write(JsonGenerator _gen, @Nullable AuthResponse _object) throws IOException {
    if (_object == null) {
      _gen.writeNull();
      return;
    }
    _gen.writeStartObject(_object);
    if (_object.token() != null) {
      _gen.writeFieldName(_token_optimized_field_name);
      _gen.writeString(_object.token());
    }
    _gen.writeEndObject();
  }
}
