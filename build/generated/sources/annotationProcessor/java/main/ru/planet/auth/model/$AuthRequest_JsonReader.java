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
public final class $AuthRequest_JsonReader implements JsonReader<AuthRequest> {
  private static final int ALL_FIELDS_RECEIVED = 0b11;

  private static final int NULLABLE_FIELDS_RECEIVED = 0b00;

  private static final SerializedString _login_optimized_field_name = new SerializedString("login");

  private static final SerializedString _password_optimized_field_name = new SerializedString("password");

  public $AuthRequest_JsonReader() {
  }

  private static String read_login(JsonParser __parser, int[] __receivedFields) throws IOException {
    var __token = __parser.nextToken();
    __receivedFields[0] = __receivedFields[0] | (1 << 0);
    if (__token == JsonToken.VALUE_STRING) {
      return __parser.getText();
    } else {
      throw new JsonParseException(__parser, "Expecting [VALUE_STRING] token for field 'login', got " + __token);
    }
  }

  private static String read_password(JsonParser __parser, int[] __receivedFields) throws
      IOException {
    var __token = __parser.nextToken();
    __receivedFields[0] = __receivedFields[0] | (1 << 1);
    if (__token == JsonToken.VALUE_STRING) {
      return __parser.getText();
    } else {
      throw new JsonParseException(__parser, "Expecting [VALUE_STRING] token for field 'password', got " + __token);
    }
  }

  @Override
  @Nullable
  public final AuthRequest read(JsonParser __parser) throws IOException {
    var __token = __parser.currentToken();
    if (__token == JsonToken.VALUE_NULL) 
      return null;
    if (__token != JsonToken.START_OBJECT) 
      throw new JsonParseException(__parser, "Expecting START_OBJECT token, got " + __token);
    var __receivedFields = new int[]{NULLABLE_FIELDS_RECEIVED};

    String login = null;
    String password = null;
    if (__parser.nextFieldName(_login_optimized_field_name)) {
      login = read_login(__parser, __receivedFields);
      if (__parser.nextFieldName(_password_optimized_field_name)) {
        password = read_password(__parser, __receivedFields);
        __token = __parser.nextToken();
        while (__token != JsonToken.END_OBJECT) {
            __parser.nextToken();
            __parser.skipChildren();
            __token = __parser.nextToken();
        }
        return new AuthRequest(login, password);
      }
    }
    __token = __parser.currentToken();
    while (__token != JsonToken.END_OBJECT) {
      if (__token != JsonToken.FIELD_NAME) 
        throw new JsonParseException(__parser, "Expecting FIELD_NAME token, got " + __token);
      var __fieldName = __parser.currentName();
      switch (__fieldName) {
        case "login" -> {
          login = read_login(__parser, __receivedFields);
        }
        case "password" -> {
          password = read_password(__parser, __receivedFields);
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
      for (int __i = 0; __i < 2; __i++) {
        if ((_nonReceivedFields & (1 << __i)) != 0) {
          __error.append(" ").append(switch (__i) {
          case 0 -> "login(login)";
          case 1 -> "password(password)";
          default -> "";
        });
        }
      }
      throw new JsonParseException(__parser, __error.toString());
    }
    return new AuthRequest(login, password);
  }
}
