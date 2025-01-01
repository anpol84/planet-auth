package ru.planet.auth.model;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.SerializedString;
import jakarta.annotation.Nullable;
import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import ru.tinkoff.kora.common.annotation.Generated;
import ru.tinkoff.kora.json.common.JsonReader;

@Generated("ru.tinkoff.kora.json.annotation.processor.reader.JsonReaderGenerator")
public final class $AuthErrorResponse_JsonReader implements JsonReader<AuthErrorResponse> {
  private static final int ALL_FIELDS_RECEIVED = 0b1;

  private static final int NULLABLE_FIELDS_RECEIVED = 0b1;

  private static final SerializedString _message_optimized_field_name = new SerializedString("message");

  public $AuthErrorResponse_JsonReader() {
  }

  private static String read_message(JsonParser __parser, int[] __receivedFields) throws
      IOException {
    var __token = __parser.nextToken();
    __receivedFields[0] = __receivedFields[0] | (1 << 0);
    if (__token == JsonToken.VALUE_STRING) {
      return __parser.getText();
    } else if (__token == JsonToken.VALUE_NULL) {
      return null;
    } else {
      throw new JsonParseException(__parser, "Expecting [VALUE_STRING, VALUE_NULL] token for field 'message', got " + __token);
    }
  }

  @Override
  @Nullable
  public final AuthErrorResponse read(JsonParser __parser) throws IOException {
    var __token = __parser.currentToken();
    if (__token == JsonToken.VALUE_NULL) 
      return null;
    if (__token != JsonToken.START_OBJECT) 
      throw new JsonParseException(__parser, "Expecting START_OBJECT token, got " + __token);
    var __receivedFields = new int[]{NULLABLE_FIELDS_RECEIVED};

    String message = null;
    if (__parser.nextFieldName(_message_optimized_field_name)) {
      message = read_message(__parser, __receivedFields);
      __token = __parser.nextToken();
      while (__token != JsonToken.END_OBJECT) {
          __parser.nextToken();
          __parser.skipChildren();
          __token = __parser.nextToken();
      }
      return new AuthErrorResponse(message);
    }
    __token = __parser.currentToken();
    while (__token != JsonToken.END_OBJECT) {
      if (__token != JsonToken.FIELD_NAME) 
        throw new JsonParseException(__parser, "Expecting FIELD_NAME token, got " + __token);
      var __fieldName = __parser.currentName();
      switch (__fieldName) {
        case "message" -> {
          message = read_message(__parser, __receivedFields);
        }
        default -> {
          __parser.nextToken();
          __parser.skipChildren();
        }
      }
      __token = __parser.nextToken();
    }
    if (__receivedFields[0] != ALL_FIELDS_RECEIVED) {
      var _nonReceivedFields = (~__receivedFields[0]) & ALL_FIELDS_RECEIVED;
      var __error = new StringBuilder("Some of required json fields were not received:");
      for (int __i = 0; __i < 1; __i++) {
        if ((_nonReceivedFields & (1 << __i)) != 0) {
          __error.append(" ").append(switch (__i) {
          case 0 -> "message(message)";
          default -> "";
        });
        }
      }
      throw new JsonParseException(__parser, __error.toString());
    }
    return new AuthErrorResponse(message);
  }
}
